package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.*;
import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원 서비스 구현체
 * @author 김지현
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	김지현        최초 생성
 * 2024.09.03   김지현        마이페이지 관련 기능 구현
 * 2024.09.10   조영욱        S3 도입으로 인한 이미지 URL 변경
 * 2024.09.11   김지현        회원 정보 조회 기능 구현
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    @Value("${S3_BUCKET_URL}")
    private String s3BucketUrl;

    /**
     * 포인트 사용 내역 조회
     *
     * @author 김지현
     */
    @Override
    public PageResponseDTO<List<PointHistoryResponseDTO>> getPointHistory(int memberId, int page, int size) {
        int offset = (page-1) * size;
        List<PointHistoryResponseDTO> pointHistoryList = memberMapper.selectPointHistoryByMemberId(memberId, offset, size);

        int totalCount = pointHistoryList.isEmpty() ? 0 : pointHistoryList.get(0).getTotalCount();
        return PageResponseDTO.<List<PointHistoryResponseDTO>>builder()
                .data(pointHistoryList)
                .totalCount(totalCount)
                .currentPage(page)
                .pageSize(size)
                .build();
    }

    /**
     * 회원 게시글 목록 조회
     *
     * @author 김지현
     */
    @Override
    public PageResponseDTO<List<PostListResponseDTO>> getMemberPost(int memberId, String orderBy, int page, int size) {
        int offset = (page-1) * size;
        List<PostListResponseDTO> postList = memberMapper.selectPostByMemberId(memberId, orderBy, offset, size);

        int totalCount = postList.isEmpty() ? 0 : postList.get(0).getTotalCount();

        // s3 버킷 이미지 url 추가
        for (PostListResponseDTO post : postList) {
            post.setImageUrl(s3BucketUrl + post.getImageUrl());
        }

        return PageResponseDTO.<List<PostListResponseDTO>>builder()
                .data(postList)
                .totalCount(totalCount)
                .currentPage(page)
                .pageSize(size)
                .build();
    }

    /**
     * 회원 코디 목록 조회
     *
     * @author 김지현
     */
    @Override
    public List<CoordinationListResponseDTO> getMemberCoordination(int memberId) {
        List<CoordinationListResponseDTO> coordinationList = memberMapper.selectCoordinationByMemberId(memberId);

        // s3 버킷 이미지 url 추가
        for (CoordinationListResponseDTO coordination : coordinationList) {
            coordination.setImageUrl(s3BucketUrl + coordination.getImageUrl());
        }

        return coordinationList;
    }

    /**
     * 코디에 사용된 아이템 조회
     *
     * @author 김지현
     */
    public List<CoordinationItemListResponseDTO> getCoordinationItem(int coordinationId) {
        List<CoordinationItemListResponseDTO> coordinationItemList = memberMapper.selectCoordinationItemByCoordinationId(coordinationId);

        // s3 버킷 이미지 url 추가
        for (CoordinationItemListResponseDTO coordinationItem : coordinationItemList) {
            coordinationItem.setImageUrl(s3BucketUrl + coordinationItem.getImageUrl());
            coordinationItem.setDetailImageUrl(s3BucketUrl + coordinationItem.getDetailImageUrl());
        }

        return coordinationItemList;
    }

    /**
     * 회원 주문 내역 전체 조회
     *
     * @author 김지현
     */
    public PageResponseDTO<List<OrdersListResponseDTO>> getMemberOrders(int memberId, int page, int size) {
        int offset = (page-1) * size;
        List<OrdersListResponseDTO> orderList = memberMapper.selectOrdersByMemberId(memberId, offset, size);

        int totalCount = orderList.isEmpty() ? 0 : orderList.get(0).getTotalCount();

        return PageResponseDTO.<List<OrdersListResponseDTO>>builder()
                .data(orderList)
                .totalCount(totalCount)
                .currentPage(page)
                .pageSize(size)
                .build();
    }

    /**
     * 회원 주문 내역 상세 조회
     *
     * @author 김지현
     */
    @Override
    public OrdersDetailResponseDTO getMemberOrdersDetail(String orderId) {
        OrdersDetailResponseDTO orderDetail = memberMapper.selectOrdersDetailByOrderId(orderId);

        // s3 버킷 이미지 url 추가
        orderDetail.setItemList(orderDetail.getItemList().stream()
                .peek(item -> {
                    item.setItemImageUrl(s3BucketUrl + item.getItemImageUrl());
                    item.setShopImageUrl(s3BucketUrl + item.getShopImageUrl());
                })
                .collect(Collectors.toList()));

        return orderDetail;
    }

    /**
     * 회원 정보 조회
     *
     * @author 김지현
     */
    @Override
    public MemberInfoResponseDTO getMemberInfo(int memberId) {
        return memberMapper.selectMemberInfoByMemberId(memberId);
    }

}
