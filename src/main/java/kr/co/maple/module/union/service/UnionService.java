package kr.co.maple.module.union.service;

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
import kr.co.maple.module.union.model.CharacterUnionDTO;
import kr.co.maple.module.union.model.UnionArtifactDTO;
import kr.co.maple.module.union.model.UnionDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnionService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final MapleCharacterApiService mapleCharacterApiService;
    @Autowired
    private MapleApiTimeCheckComponent mapleApiTimeCheckComponent;
    
	public HttpEntity<?> characterUnion(String characterName) {
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
        // 캐릭터 식별 정보
		CharacterIdDTO characterIdDTO = mapleCharacterApiService.getCharacterId(characterName);
		String ocid = characterIdDTO.getOcid();
		// 유니온 정보 조회
		UnionDTO unionDTO = mapleCharacterApiService.getCharacterUnion(previousDate, ocid);
		// 유니온 아티팩트 정보 조회
		UnionArtifactDTO unionArtifactDTO = mapleCharacterApiService.getCharacterUnionArtifact(previousDate, ocid);

		CharacterUnionDTO characterUnionDTO = CharacterUnionDTO.builder()
											  	.union(unionDTO)
											  	.unionArtifact(unionArtifactDTO)
											  	.build();
        return new ResponseEntity<>(	
                ResDTO.builder()
                        .code(0)
                        .data(characterUnionDTO)
                        .message("캐릭터 유니온 정보를 불러왔습니다.")
                        .build(),
                        HttpStatus.OK
        );
	}
}
