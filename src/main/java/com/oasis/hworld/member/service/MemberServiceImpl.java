package com.oasis.hworld.member.service;

import com.oasis.hworld.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper mapper;

    @Override
    public int test() {
        mapper.testMapper2();
        log.info("서비스 잘 됨.");
        return 0;
    }
}
