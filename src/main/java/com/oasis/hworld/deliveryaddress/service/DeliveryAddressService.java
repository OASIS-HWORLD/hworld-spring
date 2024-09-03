package com.oasis.hworld.deliveryaddress.service;

import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;
import com.oasis.hworld.deliveryaddress.dto.DeliveryAddressRequestDTO;

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
 * 2024.09.03   조영욱        배송지 추가, 삭제 구현
 * </pre>
 */
public interface DeliveryAddressService {

    /**
     * 배송지 목록 조회
     *
     * @author 조영욱
     * @apiNote 로그인한 회원의 배송지 목록을 조회한다.
     */
     List<DeliveryAddress> getDeliveryAddressList(int memberId);

    /**
     * 배송지 추가
     *
     * @author 조영욱
     */
     boolean addDeliveryAddress(DeliveryAddressRequestDTO dto, int memberId);

    /**
     * 배송지 삭제
     *
     * @author 조영욱
     */
    boolean removeDeliveryAddress(int deliveryAddressId, int memberId);

}
