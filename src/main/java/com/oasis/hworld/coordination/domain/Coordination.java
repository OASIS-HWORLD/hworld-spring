package com.oasis.hworld.coordination.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 코디 VO
 * @author 김지현
 * @since 2024.09.04
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.04  	김지현        최초 생성
 * </pre>
 */

@Data
@Builder
public class Coordination {

    // 코디 ID
    private int coordinationId;
    // 회원 ID
    private int memberId;
    // 제목
    private String title;
    // 이미지 url
    private String imageUrl;

}
