package kr.co.maple.module.skillAndSymbol.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.maple.common.model.ResDTO;
import kr.co.maple.module.main.model.MainDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillAndSymbolService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	
	public HttpEntity<?> skillAndSymbol(String characterName) {
		log.info("캐릭터 이름 --> " + characterName);
        return new ResponseEntity<>(
                ResDTO.<MainDTO>builder()
                        .code(0)
                        .message("캐릭터 스킬 및 심볼 정보를 불러왔습니다.")
                        .build(),
                        HttpStatus.OK
        );
	}
}
