package com.oasis.hworld.character.dto;

import com.oasis.hworld.character.domain.CharacterState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 캐릭터 상태 조회 Response DTO
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
@Builder
public class CharacterStateResponseDTO {
    // 속도
    private int speed;
    // 마우스 감도
    private int mouseSensitivity;
    // 소리 크기
    private int sound;
    // 캐릭터 타입
    private int characterType;

    public static CharacterStateResponseDTO from(CharacterState characterState) {
        return CharacterStateResponseDTO.builder()
                .speed(characterState.getSpeed())
                .mouseSensitivity(characterState.getMouseSensitivity())
                .sound(characterState.getSound())
                .characterType(characterState.getCharacterType())
                .build();
    }
}
