package com.oasis.hworld.character.domain;

import lombok.Data;

import java.util.Date;

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
    // 안경 ID
    private int glassesId;
    // 모자 ID
    private int capId;
    // 가방 id
    private int bagId;
    // 목걸이 id
    private int necklaceId;
    // 속도

}
