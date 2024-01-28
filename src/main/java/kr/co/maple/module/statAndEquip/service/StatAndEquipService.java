package kr.co.maple.module.statAndEquip.service;

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
import kr.co.maple.module.main.model.CharacterIdDTO;
import kr.co.maple.module.statAndEquip.model.CharacterAndroidEquipmentDTO;
import kr.co.maple.module.statAndEquip.model.CharacterCashitemEquipmentDTO;
import kr.co.maple.module.statAndEquip.model.CharacterItemEquipmentDTO;
import kr.co.maple.module.statAndEquip.model.CharacterStatAndEquipDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatAndEquipService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final MapleCharacterApiService mapleCharacterApiService;
    @Autowired
    private MapleApiTimeCheckComponent mapleApiTimeCheckComponent;
    
    @Cacheable("characterStatAndEquip")
	public HttpEntity<?> equipment(String characterName) {
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
        // 캐릭터 식별 정보
		CharacterIdDTO characterIdDTO = mapleCharacterApiService.getCharacterId(characterName);
		String ocid = characterIdDTO.getOcid();
		// 캐릭터 장착 장비 정보
		CharacterItemEquipmentDTO characterItemEquipmentDTO = mapleCharacterApiService.getCharacterItemEquipment(previousDate, ocid);
		// 캐릭터 장착 안드로이드 정보 조회
		CharacterAndroidEquipmentDTO characterAndroidEquipmentDTO = mapleCharacterApiService.getCharacterAndroidEquipment(previousDate, ocid);
		// 캐릭터 장착 캐시 장비 정보 조회
		CharacterCashitemEquipmentDTO characterCashitemEquipmentDTO = mapleCharacterApiService.getCharacterCashItemEquipment(previousDate, ocid);

		CharacterStatAndEquipDTO characterStatAndEquipDTO = CharacterStatAndEquipDTO.builder()
																.characterItemEquipment(characterItemEquipmentDTO)
																.characterAndroidEquipment(characterAndroidEquipmentDTO)
																.characterCashitemEquipment(characterCashitemEquipmentDTO)
																.build();
        log.info("캐릭터 장착 장비 정보  --> " + characterStatAndEquipDTO);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("캐릭터 장착 장비 정보를 불러왔습니다.")
                        .data(characterStatAndEquipDTO)
                        .build(),
                        HttpStatus.OK
        );
	}
}
