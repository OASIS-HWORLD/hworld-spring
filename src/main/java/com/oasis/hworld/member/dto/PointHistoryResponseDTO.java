package com.oasis.hworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 포인트 내역 응답 DTO
 * @author 김지현
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PointHistoryResponseDTO {

    // 타입
    private String type;
    // 적립/사용 양
    private int amount;
    // 설명
    private String description;
    // 생성일
    private String createdAt;

}
