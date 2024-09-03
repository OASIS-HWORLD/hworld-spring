package com.oasis.hworld.character.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 캐릭터 상태 변경 Request DTO
 * @author 조영욱
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class CharacterStateRequestDTO {
    // 캐릭터 이동속도
    private int speed;
    // 마우스 감도
    private int mouseSensitivity;
    // 소리 크기
    private int sound;
}
