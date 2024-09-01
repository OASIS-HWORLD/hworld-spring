package com.oasis.hworld.security.jwt;

import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.security.dto.JwtTokenDTO;
import com.oasis.hworld.security.service.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * JWT 토큰 생성 및 검증
 * @author 김지현
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01   김지현        최초 생성
 * </pre>
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider{

    @Setter
    private static String tokenSecretKey;

    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1800_000L;      // 30분
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 604_800_000L;  // 1주

    private Key key;

    /**
     * 비밀키 초기화
     *
     * @author 김지현
     */
    @PostConstruct
    public void init() {
        byte[] encodedKey = Base64.getEncoder().encode(tokenSecretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(encodedKey);
    }

    /**
     * AccessToken과 RefreshToken 생성
     *
     * @author 김지현
     */
    public JwtTokenDTO generateToken(Authentication authentication) {
        String nickname = authentication.getName();

        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("auth", "MEMBER");   // 모든 사용자는 MEMBER로 설정

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return new JwtTokenDTO(accessToken, refreshToken);
    }

    /**
     * Authentication 객체 생성
     *
     * @author 김지현
     */
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        // 클레임에서 권한 정보 가져옴
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"));

        // UserDetails 객체를 생성해 Authentication 반환
        CustomUserDetails principal = new CustomUserDetails(
                Member.builder()
                        .loginId(claims.getSubject())
                        .roles(claims.get("auth").toString())
                        .build()
        );
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * AccessToken을 파싱하여 claim 추출
     *
     * @author 김지현
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    /**
     * HTTP 요청의 Authorization 헤더에서 JWT 토큰 추출
     *
     * @author 김지현
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * JWT 토큰의 유효성 검사
     *
     * @author 김지현
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid or Incorrect AccessToken");
            throw new JwtException("Invalid AccessToken", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired AccessToken");
            throw new JwtException("Expired AccessToken", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token");
            throw new JwtException("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.error("Incorrect AccessToken or Input parameters");
            throw new JwtException("Incorrect AccessToken or Input parameters", e);
        }
    }

}
