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
 * 2023.09.12   조영욱        코디 추가 시 이미지 S3에 업로드
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
    // 이미지 URL
    private String imageUrl;
    // 코디 제목
    private String title;
    // 코디 상품 옵션 ID 목록
    private List<Integer> itemOptionIdList;

}
