package kr.co.maple.module.character.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.maple.common.component.MapleApiTimeCheckComponent;
import kr.co.maple.common.model.ResDTO;
import kr.co.maple.common.service.MapleCharacterApiService;
import kr.co.maple.module.main.model.CharacterIdDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final MapleCharacterApiService mapleCharacterApiService;
    @Autowired
    private MapleApiTimeCheckComponent mapleApiTimeCheckComponent;
    
	public HttpEntity<?> characterStat(String characterName) {
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
        
        
		CharacterIdDTO characterIdDTO = mapleCharacterApiService.getCharacterId(characterName);
		log.info("캐릭터 식별자 --> " + characterIdDTO);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("캐릭터 랭킹 정보를 불러왔습니다.")
                        .build(),
                        HttpStatus.OK
        );
	}
}
