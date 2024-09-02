package com.oasis.hworld.payment.controller;

import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.payment.dto.ConfirmPaymentRequestDTO;
import com.oasis.hworld.payment.dto.OrderRequestDTO;
import com.oasis.hworld.payment.dto.OrderResponseDTO;
import com.oasis.hworld.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/payments", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/order")
    public ResponseEntity<OrderResponseDTO> addOrder(@RequestBody OrderRequestDTO dto) {
        /*
        * 리턴 해줘야될거
        * orderId: 2024090222034(YYYYMMDDHHMISS)000001(SEQ)
        * orderName: (상품명) 외 (나머지상품개수)건
        * amount: 결제금액
        * */

        return ResponseEntity.ok(service.addOrder(dto));
    }

    @PostMapping("/confirm")
    public ResponseEntity<CommonResponseDTO> confirmPayment(@RequestBody ConfirmPaymentRequestDTO dto) throws Exception {

        return service.confirmPayment(dto) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "상품이 결제되었습니다.")) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(false, "결제를 실패했습니다."));
    }
}