package com.oasis.hworld.contest.domain;

import lombok.Data;

/**
 * 콘테스트 게시글 추천 VO
 * @author 정은찬
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	정은찬        최초 생성
 * </pre>
 */
@Data
public class Recommend {
    private int memberId;
    private int postId;

}
