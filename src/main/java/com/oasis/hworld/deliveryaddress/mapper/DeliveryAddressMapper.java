package com.oasis.hworld.deliveryaddress.mapper;

import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;
import com.oasis.hworld.deliveryaddress.dto.DeliveryAddressRequestDTO;
import org.apache.ibatis.annotations.Param;

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
 * 2024.09.03   조영욱        배송지 추가, 삭제 구현
 * </pre>
 */
public interface DeliveryAddressMapper {

    // 회원 ID로 배송지 목록 조회
    List<DeliveryAddress> selectDeliveryAddressByMemberId(int memberId);
    // 배송지 ID로 배송지 조회
    DeliveryAddress selectDeliveryAddressByDeliveryAddressId(int deliveryAddressId);
    // 배송지 추가
    int insertDeliveryAddress(@Param("dto") DeliveryAddressRequestDTO dto, @Param("memberId") int memberId);
    // 배송지 삭제
    int deleteDeliveryAddress(int deliveryAddressId);
}
