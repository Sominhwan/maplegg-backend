package kr.co.maple.module.characterInfo.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.maple.common.component.MapleApiTimeCheckComponent;
import kr.co.maple.common.model.ResDTO;
import kr.co.maple.common.service.MapleCharacterApiService;
import kr.co.maple.module.characterInfo.model.CharacterInfoDTO;
import kr.co.maple.module.characterInfo.model.CharacterPopularityDTO;
import kr.co.maple.module.characterInfo.model.CharacterStatDTO;
import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.CharacterIdDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterInfoService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final MapleCharacterApiService mapleCharacterApiService;
    @Autowired
    private MapleApiTimeCheckComponent mapleApiTimeCheckComponent;

    @Cacheable("characterInfo")
	public HttpEntity<?> characterStat(String characterName) {
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
        // 캐릭터 식별 정보
		CharacterIdDTO characterIdDTO = mapleCharacterApiService.getCharacterId(characterName);
		String ocid = characterIdDTO.getOcid();
		// 캐릭터  기본 정보
		CharacterBasicDTO characterBasicDTO = mapleCharacterApiService.getCharacterBasic(previousDate, ocid);
		// 캐릭터 인기도
		CharacterPopularityDTO characterPopularityDTO = mapleCharacterApiService.getCharacterPopularity(previousDate, ocid);
		// 캐릭터 종합 능력치 
		CharacterStatDTO characterStatDTO = mapleCharacterApiService.getCharacterStat(previousDate, ocid);
		
		CharacterInfoDTO characterInfoDTO = CharacterInfoDTO.builder()
												.characterBasicInfo(characterBasicDTO)
												.characterPopularityInfo(characterPopularityDTO)
												.characterStatInfo(characterStatDTO)
												.build();
		
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("캐릭터 종합 능력치 정보를 불러왔습니다.")
                        .data(characterInfoDTO)
                        .build(),
                        HttpStatus.OK
        );
	}
}
