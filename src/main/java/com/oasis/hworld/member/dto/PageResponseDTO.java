package com.oasis.hworld.member.dto;

import lombok.*;

import java.util.List;

/**
 * 페이징 처리 응답 DTO
 * @author 김지현
 * @since 2024.09.12
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PageResponseDTO<T> {
    // 데이터
    private T data;
    // 전체 데이터 수
    private int totalCount;
    // 현재 페이지 번호
    private int currentPage;
    // 페이지 크기
    private int pageSize;
}
