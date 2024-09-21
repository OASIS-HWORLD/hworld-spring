package com.oasis.hworld.contest.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.common.domain.ItemCategory;
import com.oasis.hworld.common.file.S3Uploader;
import com.oasis.hworld.contest.dto.*;
import com.oasis.hworld.contest.mapper.ContestMapper;
import com.oasis.hworld.coordination.dto.CoordinationItemResponseDTO;
import com.oasis.hworld.coordination.mapper.CoordinationMapper;
import com.oasis.hworld.quest.mapper.QuestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.oasis.hworld.common.exception.ErrorCode.*;

/**
 * 콘테스트 서비스 구현체
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합, 게시글 상세 조회 메소드 추가
 * 2024.09.02   정은찬        회원 ID를 통한 코디 목록 조회 메소드, 진행중인 콘테스트 게시글 등록 메소드, 댓글 등록/삭제 메소드, 게시글 추천 여부 확인 메소드 추가
 * 2024.09.03   정은찬        콘테스트 게시글 추천하기 메소드, 게시글 추천 취소하기 메소드, 게시글 목록 조회 / 상세보기 메소드 추천여부 확인, 게시글 삭제하기 메소드 추가
 * 2024.09.10   조영욱        S3 도입으로 인한 이미지 URL 변경
 * 2024.09.12   조영욱        베스트 코디 조회 추가
 * 2024.09.15   조영욱        게시글 생성 시 이미지 업로드 추가
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final ContestMapper mapper;
    private final CoordinationMapper coordinationMapper;
    private final QuestMapper questMapper;
    private final S3Uploader s3Uploader;
    @Value("${S3_BUCKET_URL}")
    private String s3BucketUrl;

    /**
     * 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     */
    public PostResponseDTO getContestPostList(int page, int amount, String contestStatus, String sortBy, int memberId, String month) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        List<PostSummaryDTO> postSummaryList = mapper.selectContestPostList(page, amount, formattedDate, sortBy, contestStatus, month);

        // 모든 postId 추출
        List<Integer> postIds = postSummaryList.stream()
                .map(PostSummaryDTO::getPostId)
                .collect(Collectors.toList());

        // 추천받은 postId 목록 조회
        List<Integer> recommendedPostIds = mapper.getRecommendedPosts(memberId, postIds);

        // 각 PostSummary 객체에 대한 추천 여부 설정
        postSummaryList.forEach(postSummary -> {
            if (recommendedPostIds.contains(postSummary.getPostId())) {
                postSummary.setIsRecommended(true);
            } else {
                postSummary.setIsRecommended(false);
            }

            // s3 버킷 이미지 url 추가
            postSummary.setImageUrl(s3BucketUrl + postSummary.getImageUrl());
            postSummary.setPostImageUrl(s3BucketUrl + postSummary.getPostImageUrl());
        });

        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setPostList(postSummaryList);
        postResponseDTO.setTotalCount(mapper.selectContestPostTotalCount(formattedDate, contestStatus, month));

        return postResponseDTO;
    }

    /**
     * 게시글 ID를 통해 콘테스트 게시글 상세 조회
     *
     * @author 정은찬
     */
    public PostDetailResponseDTO getPostDetail(int postId, int memberId) {
        PostDetailResponseDTO postDetail = mapper.selectContestPostDetailByPostId(postId);

        // s3 버킷 이미지 url 추가
        postDetail.setImageUrl(s3BucketUrl + postDetail.getImageUrl());
        postDetail.setPostImageUrl(s3BucketUrl + postDetail.getPostImageUrl());
        postDetail.getItemList().forEach(itemDTO -> {
            itemDTO.setCategoryName(ItemCategory.getCategoryName(itemDTO.getCategoryId()));
            // s3 버킷 이미지 url 추가
            itemDTO.setItemImageUrl(s3BucketUrl + itemDTO.getItemImageUrl());
            itemDTO.setShopImageUrl(s3BucketUrl + itemDTO.getShopImageUrl());
        });
        if(postDetail == null) {
            throw new CustomException(POST_NOT_EXIST);
        }
        if(checkRecommend(memberId, postId)) {
            postDetail.setIsRecommended(true);
        } else {
            postDetail.setIsRecommended(false);
        }

        return postDetail;
    }

    /**
     * 회원 ID를 통해 코디 목록 조회
     *
     * @author 정은찬
     */
    public List<CoordinationResponseDTO> getCoordinationList(int memberId) {
        List<CoordinationResponseDTO> coordinationList = mapper.selectCoordinationListByMemberId(memberId);

        coordinationList.forEach(coordination -> {
            coordination.getItemList().forEach(item -> {
                item.setCategoryName(ItemCategory.getCategoryName(item.getCategoryId()));
                // s3 버킷 이미지 url 추가
                item.setItemImageUrl(s3BucketUrl + item.getItemImageUrl());
            });

            // s3 버킷 이미지 url 추가
            coordination.setCoordinationImageUrl(s3BucketUrl + coordination.getCoordinationImageUrl());
        });
        return coordinationList;
    }

    /**
     * 진행중인 콘테스트 게시글 등록
     *
     * @author 정은찬
     */
    public boolean addContestPost(int memberId, PostRequestDTO postRequestDTO, MultipartFile file) {

        String uploadedImageUrl = s3Uploader.uploadImage(file);

        int coordinationId = postRequestDTO.getCoordinationId();

//        // 이미 같은 코디 게시글 존재
//        if(mapper.selectContestPostByMemberIdAndCoordinationId(memberId, coordinationId) != null) {
//            return false;
//        }
//

        List<CoordinationItemResponseDTO> coordinationItemResponseDTOList =
                coordinationMapper.selectCoordinationItemByCoordinationId(coordinationId);

        for (CoordinationItemResponseDTO coordinationItem : coordinationItemResponseDTOList) {
            if (coordinationItem.getItemId() == 1 || coordinationItem.getItemId() == 2) {
                questMapper.updateStatus(1, memberId, 2);
                break;
            }
        }

       return mapper.insertContestPost(memberId, postRequestDTO, uploadedImageUrl) == 1;
    }

    /**
     * 콘테스트 게시글 댓글 등록
     *
     * @author 정은찬
     */
    @Transactional
    public boolean addReply(int memberId, ReplyRequestDTO replyRequestDTO) {
        int result = 0;
        if(mapper.insertReply(memberId, replyRequestDTO) == 1) {
            result = mapper.updateIncreaseReplyCount(replyRequestDTO.getPostId());
        }
        return result == 1;
    }

    /**
     * 콘테스트 게시글 댓글 삭제
     *
     * @author 정은찬
     */
    @Transactional
    public boolean removeReply(int memberId, int postId, int replyId) {
        int result = 0;
        if(mapper.deleteReply(memberId, replyId) == 1) {
            result = mapper.updateDecreaseReplyCount(postId);
        }
        return result == 1;
    }

    /**
     * 콘테스트 게시글 추천 여부 확인
     *
     * @author 정은찬
     */
    private boolean checkRecommend(int memberId, int postId) {
        return mapper.selectRecommendByMemberIdAndPostId(memberId, postId) != null;
    }

    /**
     * 콘테스트 게시글 추천하기
     *
     * @author 정은찬
     */
    @Transactional
    public boolean addRecommend(int memberId, int postId) {
        // 이미 추천한 게시글
        if(checkRecommend(memberId, postId)) {
            return false;
        }

        // 추천 등록 및 게시글 추천수 업데이트를 위한 매개변수 설정
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("postId", postId);

        mapper.insertRecommendAndUpdateRecommendCount(params);
        int result = (Integer) params.get("totalRowsAffected");

        log.info("result :  " + result);
        return result >= 2;
    }

    /**
     * 콘테스트 게시글 추천 취소하기
     *
     * @author 정은찬
     */
    @Transactional
    public boolean removeRecommend(int memberId, int postId) {
        // 추천 삭제 및 게시글 추천수 업데이트를 위한 매개변수 설정
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("postId", postId);

        mapper.deleteRecommendAndUpdateRecommendCount(params);
        int result = (Integer) params.get("totalRowsAffected");

        log.info("result :  " + result);
        return result >= 2;
    }

    /**
     * 콘테스트 게시글 삭제하기
     *
     * @author 정은찬
     */
    public boolean removePost(int memberId, int postId) {
        return mapper.deletePostByMemberIdAndPostId(memberId, postId)==1;
    }

    /**
     * 월을 통한 콘테스트 게시글 수상작 목록 조회
     *
     * @author 정은찬
     */
    public List<PostAwardDTO> getPostAwardList(int memberId, String month) {
        List<PostAwardDTO> postAwardList = mapper.selectPostAwardListByMonth(month);

        // 모든 postId 추출
        List<Integer> postIds = postAwardList.stream()
                .map(PostAwardDTO::getPostId)
                .collect(Collectors.toList());

        // 추천받은 postId 목록 조회
        List<Integer> recommendedPostIds = mapper.getRecommendedPosts(memberId, postIds);

        // 각 PostSummary 객체에 대한 추천 여부 설정
        postAwardList.forEach(postAward -> {
            if (recommendedPostIds.contains(postAward.getPostId())) {
                postAward.setIsRecommended(true);
            } else {
                postAward.setIsRecommended(false);
            }

            // s3 버킷 이미지 url 추가
            postAward.setImageUrl(s3BucketUrl + postAward.getImageUrl());
            postAward.setPostImageUrl(s3BucketUrl + postAward.getPostImageUrl());
        });

        return postAwardList;
    }

    /**
     * 베스트 콘테스트 게시글 목록 조회
     *
     * @author 조영욱
     */
    @Override
    public PostResponseDTO getBestContestPostList(int memberId) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        List<PostSummaryDTO> postSummaryList = mapper.selectContestPostListOrderByRecommend(formattedDate);

        // 모든 postId 추출
        List<Integer> postIds = postSummaryList.stream()
                .map(PostSummaryDTO::getPostId)
                .collect(Collectors.toList());

        // 추천받은 postId 목록 조회
        List<Integer> recommendedPostIds = mapper.getRecommendedPosts(memberId, postIds);

        // 각 PostSummary 객체에 대한 추천 여부 설정
        postSummaryList.forEach(postSummary -> {
            if (recommendedPostIds.contains(postSummary.getPostId())) {
                postSummary.setIsRecommended(true);
            } else {
                postSummary.setIsRecommended(false);
            }

            // s3 버킷 이미지 url 추가
            postSummary.setImageUrl(s3BucketUrl + postSummary.getImageUrl());
            postSummary.setPostImageUrl(s3BucketUrl + postSummary.getPostImageUrl());
        });
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setPostList(postSummaryList);
        postResponseDTO.setTotalCount(mapper.selectContestPostTotalCount(formattedDate, null, null));

        return postResponseDTO;
    }
}
