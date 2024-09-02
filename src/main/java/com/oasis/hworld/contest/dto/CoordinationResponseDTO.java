package com.oasis.hworld.contest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 코디 DTO
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
@Setter
@Getter
@ToString
public class CoordinationResponseDTO {
    private int coordinationId;
    private String title;
    private String coordinationImageUrl;
    private List<CoordinationItemDTO> itemList;
}
