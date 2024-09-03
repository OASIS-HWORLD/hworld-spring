package com.oasis.hworld.deliveryaddress.mapper;

import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;

import java.util.List;

/**
 * 배송지 Mybatis 인터페이스
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
public interface DeliveryAddressMapper {

    // 회원 ID로 배송지 목록 조회
    List<DeliveryAddress> selectDeliveryAddressByMemberId(int memberId);
    // 배송지 ID로 배송지 조회
    DeliveryAddress selectDeliveryAddressByDeliveryAddressId(int deliveryAddressId);

}
