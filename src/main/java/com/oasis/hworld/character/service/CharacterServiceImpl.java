package com.oasis.hworld.character.service;

import com.oasis.hworld.character.mapper.CharacterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * 캐릭터 서비스 구현체
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
@Service
@Log4j
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper mapper;

}
