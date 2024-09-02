package com.oasis.hworld.deliveryaddress.service;

import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;
import com.oasis.hworld.deliveryaddress.mapper.DeliveryAddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 배송지 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressMapper mapper;

    /**
     * 배송지 목록 조회
     *
     * @author 조영욱
     */
    @Override
    public List<DeliveryAddress> getDeliveryAddressList(int memberId) {
        return mapper.selectDeliveryAddressByMemberId(memberId);
    }

    /**
     * 배송지 하나 조회
     *
     * @author 조영욱
     */
    @Override
    public DeliveryAddress getDeliveryAddress(int deliveryAddressId) {
        return mapper.selectDeliveryAddressByDeliveryAddressId(deliveryAddressId);
    }

}
