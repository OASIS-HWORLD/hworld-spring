package com.oasis.hworld.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasis.hworld.common.exception.ErrorCode;
import com.oasis.hworld.common.exception.ErrorResponse;
import com.oasis.hworld.member.service.AuthService;
import com.oasis.hworld.security.dto.JwtTokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.oasis.hworld.common.exception.ErrorCode.FORBIDDEN_REQUEST;

/**
 * JWT 인증 필터
 * @author 김지현
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01   김지현        최초 생성
 * 2024.09.15   김지현        권한에 따른 페이지 요청 처리 수정
 * </pre>
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthService authService;

    /**
     * JWT 토큰 인증을 위한 HTTP 요청 처리
     *
     * @author 김지현
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request -> " + request.toString());
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        // 누구나 접근 가능한 페이지에 대한 요청 처리
        // 1. 회원가입 및 로그인 페이지
        if (requestURI.equals("/members/login") || requestURI.equals("/members/sign-up") ||
                requestURI.equals("/members/check-id") || requestURI.equals("/members/check-nickname")) {
            log.info("로그인 및 회원가입 페이지 접근: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 2. 공지사항 페이지
        if (requestURI.startsWith("/notices")) {
            log.info("공지사항 페이지 접근: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 콘테스트 관련 페이지
        if (method.equals("GET") && (requestURI.equals("/contest/posts"))
                || requestURI.equals("/contest/posts/*")
                || requestURI.equals("/contest/")
                || requestURI.equals("/contest/posts/award")
                || requestURI.equals("/contest/posts/best")
                || requestURI.equals("/shop/item/*")) {
            log.info("콘테스트 관련 전체 공개 페이지 접근: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        JwtTokenDTO jwtTokenDTO = jwtTokenProvider.resolveToken(request);

        try {
            if (jwtTokenDTO.getAccessToken() == null) {
                throw new RuntimeException("토큰이 존재하지 않거나 유효하지 않습니다.");
            }

            String accessToken = jwtTokenDTO.getAccessToken();
            // AccessToken이 유효한지 검사
            jwtTokenProvider.validateAccessToken(accessToken);

            // 인증 객체 생성
            Authentication auth = jwtTokenProvider.getAuthentication(accessToken);

            // SecurityContextHolder에 인증 객체 저장
            SecurityContextHolder.getContext().setAuthentication(auth);
            request.setAttribute("loginId", auth.getName());

            // 다음 필터로 요청 전달
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            try {
                // AccessToken 만료 시 RefreshToken을 통해 AccessToken 재발급
                log.info("access token 만료됨");

                if (jwtTokenDTO.getRefreshToken() == null) {
                    throw new RuntimeException("토큰이 존재하지 않거나 유효하지 않습니다.");
                }

                String refreshToken = jwtTokenDTO.getRefreshToken();
                // RefreshToken이 유효한지 검사
                jwtTokenProvider.validateRefreshToken(refreshToken);

                // loginId 가져온 후 loginId, refreshToken을 바탕으로 JWT 토큰 재발급
                String loginId = jwtTokenProvider.parseClaims(refreshToken).getSubject();
                log.info("loginId = " + loginId);
                log.info("refresh token = " + refreshToken);
                JwtTokenDTO newJwtTokenDTO = authService.reissueToken(loginId, refreshToken);

                // 인증 객체 생성
                Authentication auth = jwtTokenProvider.getAuthentication(newJwtTokenDTO.getAccessToken());

                // SecurityContextHolder에 인증 객체 저장
                SecurityContextHolder.getContext().setAuthentication(auth);
                request.setAttribute("loginId", auth.getName());

                // header에 token값 세팅
                response.setHeader("auth", "Bearer " + newJwtTokenDTO.getAccessToken());
                response.setHeader("refresh", "Bearer " + newJwtTokenDTO.getRefreshToken());

                // 다음 필터로 요청 전달
                filterChain.doFilter(request, response);
            } catch (Exception e2) {
                setErrorResponse(response, FORBIDDEN_REQUEST);
            }
        } catch (Exception e3) {
            setErrorResponse(response, FORBIDDEN_REQUEST);
        }
    }

    public static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .errorCode(errorCode.name())
                .message(errorCode.getMessage())
                .build();

        // 토큰 에러 시 로그아웃
        response.setHeader("auth", "");
        response.setHeader("refresh", "");

        ResponseEntity<ErrorResponse> error =
                new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
        String s = objectMapper.writeValueAsString(error);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(s);
    }
}
