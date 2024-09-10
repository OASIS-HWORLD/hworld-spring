package com.oasis.hworld.member.dto;

import lombok.*;

/**
 * 회원 코디 내역 응답 DTO
 * @author 김지현
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CoordinationListResponseDTO {

    // 코디 ID
    private int coordinationId;
    // 제목
    private String title;
    // 이미지 url
    private String imageUrl;

}
