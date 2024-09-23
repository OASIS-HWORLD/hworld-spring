package com.oasis.hworld.character.domain;

import lombok.Data;

/**
 * 캐릭터 상태 VO
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
@Data
public class CharacterState {
    // 회원 ID
    private int memberId;
    // 속도
    private int speed;
    // 마우스 감도
    private int mouseSensitivity;
    // 소리 크기
    private int sound;
    // 캐릭터 타입
    private int characterType;
}
