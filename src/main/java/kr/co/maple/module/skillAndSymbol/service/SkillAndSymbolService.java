package kr.co.maple.module.skillAndSymbol.service;

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
import kr.co.maple.module.main.model.MainDTO;
import kr.co.maple.module.skillAndSymbol.model.HexamatrixDTO;
import kr.co.maple.module.skillAndSymbol.model.HexamatrixStatDTO;
import kr.co.maple.module.skillAndSymbol.model.SkillAndSymbolDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillAndSymbolService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
    private final MapleCharacterApiService mapleCharacterApiService;
    @Autowired
    private MapleApiTimeCheckComponent mapleApiTimeCheckComponent;
    @Cacheable("characterSkillAndSymbol")
	public HttpEntity<?> skillAndSymbol(String characterName) {
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
        // 캐릭터 식별 정보
		CharacterIdDTO characterIdDTO = mapleCharacterApiService.getCharacterId(characterName);
		String ocid = characterIdDTO.getOcid();
        // hexa 코어 정보 조회
        HexamatrixDTO hexamatrixDTO = mapleCharacterApiService.getCharacterHexamatrix(previousDate, ocid);
		// hexa 매트릭스 설정 hexa 스탯 정보 조회
        HexamatrixStatDTO hexamatrixStatDTO = mapleCharacterApiService.getCharacterHexamatrixStat(previousDate, ocid);
        
        SkillAndSymbolDTO skillAndSymbolDTO = SkillAndSymbolDTO.builder()
        										.hexamatrix(hexamatrixDTO)
        										.hexamatrixStat(hexamatrixStatDTO)
        										.build();
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .data(skillAndSymbolDTO)
                        .message("캐릭터 스킬 및 심볼 정보를 불러왔습니다.")
                        .build(),
                        HttpStatus.OK
        );
	}
}
