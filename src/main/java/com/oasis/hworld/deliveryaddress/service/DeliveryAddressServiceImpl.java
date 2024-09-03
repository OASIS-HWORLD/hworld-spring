package com.oasis.hworld.deliveryaddress.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;
import com.oasis.hworld.deliveryaddress.dto.DeliveryAddressRequestDTO;
import com.oasis.hworld.deliveryaddress.mapper.DeliveryAddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

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
 * 2024.09.03   조영욱        배송지 추가, 삭제 구현
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
     * @apiNote 로그인한 회원의 배송지 목록을 조회한다.
     */
    @Override
    public List<DeliveryAddress> getDeliveryAddressList(int memberId) {
        return mapper.selectDeliveryAddressByMemberId(memberId);
    }

    /**
     * 배송지 추가
     *
     * @author 조영욱
     */
    @Override
    public boolean addDeliveryAddress(DeliveryAddressRequestDTO dto, int memberId) {
        final int MAX_DELIVERY_ADDRESS_COUNT = 10;
        List<DeliveryAddress> deliveryAddressList = mapper.selectDeliveryAddressByMemberId(memberId);

        // 배송지 최대 개수 제한
        if (deliveryAddressList.size() >= MAX_DELIVERY_ADDRESS_COUNT) {
            throw new CustomException(TOO_MANY_DELIVERY_ADDRESSES);
        }

        return mapper.insertDeliveryAddress(dto, memberId) == 1;
    }

    /**
     * 배송지 삭제
     *
     * @author 조영욱
     */
    @Override
    public boolean removeDeliveryAddress(int deliveryAddressId, int memberId) {
        DeliveryAddress deliveryAddress = mapper.selectDeliveryAddressByDeliveryAddressId(deliveryAddressId);

        // 배송지가 없거나, 본인의 배송지가 아닌 경우 예외
        if (deliveryAddress == null || deliveryAddress.getMemberId() != memberId) {
            throw new CustomException(DELIVERY_ADDRESS_NOT_EXIST);
        }

        return mapper.deleteDeliveryAddress(deliveryAddressId) == 1;
    }
}
