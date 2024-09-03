package com.oasis.hworld.deliveryaddress.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 배송지 Request VO
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
public class DeliveryAddressRequestDTO {
    // 회원 ID
    private int memberId;
    // 수령자 이름
    private String name;
    // 수령자 전화번호
    private String phone;
    // 수령자 이메일
    private String email;
    // 수령자 주소
    private String location;
}
