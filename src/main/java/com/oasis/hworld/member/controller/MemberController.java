package com.oasis.hworld.member.controller;


import com.oasis.hworld.common.dto.CommonResponseDTO;
import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.member.dto.*;
import com.oasis.hworld.member.service.AuthService;
import com.oasis.hworld.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 회원 컨트롤러
 * @author 김지현
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	김지현        최초 생성
 * 2024.09.01   김지현        로그인 기능 구현
 * 2024.09.03   김지현        마이페이지 관련 기능 구현
 * 2024.09.11   김지현        회원 정보 조회 기능 구현
 * 2024.09.12   김지현        페이징 처리
 * </pre>
 */
@RestController
@RequestMapping(value="/members", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    /**
     * 회원가입
     *
     * @author 김지현
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDTO));
    }

    /**
     * 로그인
     *
     * @author 김지현
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponseDTO> login(HttpServletResponse response, @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        response.setHeader("auth", "Bearer " + loginResponseDTO.getAccessToken());
        response.setHeader("refresh", "Bearer " + loginResponseDTO.getRefreshToken());
        return ResponseEntity.ok(new CommonResponseDTO(true, "로그인 성공"));
    }

    /**
     * 아이디 중복 검사
     *
     * @author 김지현
     */
    @GetMapping("/check-id")
    public ResponseEntity<CommonResponseDTO> checkIdAvailability(@RequestParam("loginId") String loginId) {
        return authService.checkIdAvailability(loginId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 아이디입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용 중인 아이디입니다."));
    }

    /**
     * 닉네임 중복 검사
     *
     * @author 김지현
     */
    @GetMapping("/check-nickname")
    public ResponseEntity<CommonResponseDTO> checkNicknameAvailability(@RequestParam("nickname") String nickname) {
        return authService.checkNicknameAvailability(nickname) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 닉네임입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용 중인 닉네임입니다."));
    }

    /**
     * 포인트 사용 내역 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-points")
    public ResponseEntity<PageResponseDTO<List<PointHistoryResponseDTO>>> getPointHistory(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            @MemberId int memberId) {
        return ResponseEntity.ok(memberService.getPointHistory(memberId, page, size));
    }

    /**
     * 회원 게시글 목록 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-posts")
    public ResponseEntity<PageResponseDTO<List<PostListResponseDTO>>> getMemberPost(
            @RequestParam("orderBy") String orderBy,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            @MemberId int memberId) {
        return ResponseEntity.ok(memberService.getMemberPost(memberId, orderBy, page, size));
    }

    /**
     * 회원 코디 목록 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-coordinations")
    public ResponseEntity<List<CoordinationListResponseDTO>> getMemberCoordination(@MemberId int memberId) {
        return ResponseEntity.ok(memberService.getMemberCoordination(memberId));
    }

    /**
     * 코디에 사용된 아이템 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-coordinations/{coordinationId}")
    public ResponseEntity<List<CoordinationItemListResponseDTO>> getCoordinationItem(@PathVariable("coordinationId") int coordinationId) {
        return ResponseEntity.ok(memberService.getCoordinationItem(coordinationId));
    }

    /**
     * 회원 주문 내역 전체 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-orders")
    public ResponseEntity<PageResponseDTO<List<OrdersListResponseDTO>>> getMemberOrders(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            @MemberId int memberId) {
        return ResponseEntity.ok(memberService.getMemberOrders(memberId, page, size));
    }

    /**
     * 회원 주문 내역 상세 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-orders/{orderId}")
    public ResponseEntity<OrdersDetailResponseDTO> getMemberOrdersDetail(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(memberService.getMemberOrdersDetail(orderId));
    }

    /**
     * 회원 정보 조회
     *
     * @author 김지현
     */
    @GetMapping("/my-info")
    public ResponseEntity<MemberInfoResponseDTO> getMemberInfo(@MemberId int memberId) {
        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }
}
