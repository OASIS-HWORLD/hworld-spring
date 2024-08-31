package com.oasis.hworld.member.service;

import com.oasis.hworld.member.dto.SignUpRequestDTO;

public interface AuthService {

    int signUp(SignUpRequestDTO signUpRequestDTO);

    boolean checkIdAvailability(String loginId);

}
