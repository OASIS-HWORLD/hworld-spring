package com.oasis.hworld.security.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.oasis.hworld.common.exception.ErrorCode.NOT_VALID_USER_INFORMATION;

/**
 * Spring Security의 UserDetailsService 인터페이스 구현체
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
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.selectMemberByLoginId(username);
        if (member == null) {
            throw new CustomException(NOT_VALID_USER_INFORMATION);
        }

        return new CustomUserDetails(member);
    }
}
