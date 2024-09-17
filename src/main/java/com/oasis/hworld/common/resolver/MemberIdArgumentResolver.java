package com.oasis.hworld.common.resolver;

import com.oasis.hworld.common.annotation.MemberId;
import com.oasis.hworld.member.domain.Member;
import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberMapper memberMapper;

    public MemberIdArgumentResolver(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MemberId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // 로그인 ID가 없을 경우 0 반환
        String loginId = (String) request.getAttribute("loginId");
        if (loginId == null) {
            return 0;
        }

        Member member = memberMapper.selectMemberByLoginId(loginId);

        // Member가 존재하지 않으면 0 반환
        if (member == null) {
            return 0;
        }
        return member.getMemberId();
    }
}
