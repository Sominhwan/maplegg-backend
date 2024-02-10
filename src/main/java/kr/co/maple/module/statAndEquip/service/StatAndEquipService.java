package kr.co.maple.module.statAndEquip.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import kr.co.maple.module.main.model.AchievementRankingDTO;
import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.CharacterIdDTO;
import kr.co.maple.module.main.model.DojangRankingDTO;
import kr.co.maple.module.main.model.TheseedRankingDTO;
import kr.co.maple.module.main.model.UnionRankingDTO;
import kr.co.maple.module.statAndEquip.model.CharacterAndroidEquipmentDTO;
import kr.co.maple.module.statAndEquip.model.CharacterCashitemEquipmentDTO;
import kr.co.maple.module.statAndEquip.model.CharacterDojangDTO;
import kr.co.maple.module.statAndEquip.model.CharacterItemEquipmentDTO;
import kr.co.maple.module.statAndEquip.model.CharacterPetEquipmentDTO;
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
    	// yyyy-mm-dd
        String currentDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 0);
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
        // 캐릭터 식별 정보
		CharacterIdDTO characterIdDTO = mapleCharacterApiService.getCharacterId(characterName);
		String ocid = characterIdDTO.getOcid();
		// 캐릭터 기본 정보
		CharacterBasicDTO characterBasicDTO = mapleCharacterApiService.getCharacterBasic(previousDate, ocid);
		// 캐릭터 장착 장비 정보
		CharacterItemEquipmentDTO characterItemEquipmentDTO = mapleCharacterApiService.getCharacterItemEquipment(previousDate, ocid);
		// 캐릭터 장착 안드로이드 정보 조회
		CharacterAndroidEquipmentDTO characterAndroidEquipmentDTO = mapleCharacterApiService.getCharacterAndroidEquipment(previousDate, ocid);
		// 캐릭터 장착 캐시 장비 정보 조회
		CharacterCashitemEquipmentDTO characterCashitemEquipmentDTO = mapleCharacterApiService.getCharacterCashItemEquipment(previousDate, ocid);
		// 캐릭터 장착 펫 정보 조회
		CharacterPetEquipmentDTO characterPetEquipmentDTO = mapleCharacterApiService.getCharacterPetEquipment(previousDate, ocid);
		// 무릉도장 최고기록 정보 조회
		CharacterDojangDTO characterDojangDTO = mapleCharacterApiService.getCharacterDojang(previousDate, ocid);
		// 더 서드 최고기록
		List<TheseedRankingDTO> theseedRankingDTO = mapleCharacterApiService.getTheseedRankingList(previousDate, null, ocid);
		Optional<TheseedRankingDTO> theseedRanking = theseedRankingDTO != null && !theseedRankingDTO.isEmpty() ? Optional.of(theseedRankingDTO.get(0)) : Optional.empty();
		// 업적
		List<AchievementRankingDTO> achievementRankingDTO = mapleCharacterApiService.getAchievementRankingList(previousDate, ocid);
		Optional<AchievementRankingDTO> achievementRanking = achievementRankingDTO != null && !achievementRankingDTO.isEmpty() ? Optional.of(achievementRankingDTO.get(0)) : Optional.empty();
		// 유니온
		List<UnionRankingDTO> unionRankingDTO = mapleCharacterApiService.getUnionRankingList(previousDate, null, ocid);
		Optional<UnionRankingDTO> unionRanking = unionRankingDTO != null && !unionRankingDTO.isEmpty() ? Optional.of(unionRankingDTO.get(0)) : Optional.empty();
		
		CharacterStatAndEquipDTO characterStatAndEquipDTO = CharacterStatAndEquipDTO.builder()
								.characterBasic(characterBasicDTO)
								.characterItemEquipment(characterItemEquipmentDTO)
								.characterAndroidEquipment(characterAndroidEquipmentDTO)
								.characterCashitemEquipment(characterCashitemEquipmentDTO)
								.characterPetEquipment(characterPetEquipmentDTO)
								.characterDojangRanking(characterDojangDTO)
								.characterTheseedRanking(theseedRanking.orElse(null))
								.characterAchievementRanking(achievementRanking.orElse(null))
								.characterUnionRanking(unionRanking.orElse(null))
								.build();
     
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("캐릭터 스탯/장비 정보를 불러왔습니다.")
                        .data(characterStatAndEquipDTO)
                        .build(),
                        HttpStatus.OK
        );
	}
}
