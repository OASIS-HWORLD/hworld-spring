package com.oasis.hworld.deliveryaddress.service;

import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;

import java.util.List;

/**
 * 배송지 서비스 인터페이스
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
public interface DeliveryAddressService {

    /**
     * 배송지 목록 조회
     *
     * @author 조영욱
     */
     List<DeliveryAddress> getDeliveryAddressList(int memberId);

    /**
     * 배송지 하나 조회
     *
     * @author 조영욱
     */
    DeliveryAddress getDeliveryAddress(int deliveryAddressId);

}
