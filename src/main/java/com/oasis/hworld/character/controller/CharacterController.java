package com.oasis.hworld.character.controller;

import com.oasis.hworld.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 캐릭터 컨트롤러
 * @author 조영욱
 * @since 2024.09.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.03  	조영욱        최초 생성
 * </pre>
 */
@RestController
@RequestMapping(value="/characters", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService service;

}
