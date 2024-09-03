package com.oasis.hworld.coordination.dto;

import lombok.*;

import java.util.List;

/**
 * 코디 저장 요청 DTO
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CoordinationRequestDTO {

    // 코디 ID
    private int coordinationId;
    // 회원 ID
    private int memberId;
    // 코디 제목
    private String title;
    // 코디 이미지 url
    private String imageUrl;
    // 코디 상품 옵션 ID 목록
    private List<Integer> itemOptionIdList;

}
