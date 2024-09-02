package com.oasis.hworld.deliveryaddress.domain;

import lombok.Data;

import java.util.Date;

/**
 * 배송지 VO
 * @author 조영욱
 * @since 2024.09.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.02  	조영욱        최초 생성
 * </pre>
 */
@Data
public class DeliveryAddress {
    // 배송지 ID
    private int deliveryAddressId;
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
    // 배송지 생성일
    private Date createdAt;
}
