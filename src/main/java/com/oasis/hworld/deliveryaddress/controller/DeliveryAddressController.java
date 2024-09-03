package com.oasis.hworld.deliveryaddress.controller;

import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.deliveryaddress.domain.DeliveryAddress;
import com.oasis.hworld.deliveryaddress.dto.DeliveryAddressRequestDTO;
import com.oasis.hworld.deliveryaddress.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 배송지 컨트롤러
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
@RestController
@RequestMapping(value="/delivery-addresses", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class DeliveryAddressController {

    private final DeliveryAddressService service;

    /**
     * 배송지 목록 조회
     *
     * @author 조영욱
     * @apiNote 로그인한 회원의 배송지 목록을 조회한다.
     */
    @GetMapping("")
    public ResponseEntity<List<DeliveryAddress>> getDeliveryAddressList() {
        // todo: memberId 로직 추가
        return ResponseEntity.ok(service.getDeliveryAddressList(1));
    }

    @PostMapping("")
    public ResponseEntity<CommonResponseDTO> addDeliveryAddress(@RequestBody DeliveryAddressRequestDTO dto) {
        // todo: memberId 로직 추가
        return service.addDeliveryAddress(dto, 1) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "배송지가 추가되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "배송지 추가를 실패하였습니다."));
    }

    @DeleteMapping("/{deliveryAddressId}")
    public ResponseEntity<CommonResponseDTO> removeDeliveryAddress(@PathVariable("deliveryAddressId") int deliveryAddressId) {
        // todo: memberId 로직 추가
        return service.removeDeliveryAddress(deliveryAddressId, 1) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "배송지를 삭제하였습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "배송지 삭제를 실패하였습니다."));
    }
}
