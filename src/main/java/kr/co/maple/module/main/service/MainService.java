package kr.co.maple.module.main.service;

import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import kr.co.maple.common.component.CommonParamsComponent;
import kr.co.maple.common.model.ResDTO;
import kr.co.maple.common.service.WebClientService;
import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.CharacterIdDTO;
import kr.co.maple.module.main.model.DojangRankingDTO;
import kr.co.maple.module.main.model.DojangRankingListDTO;
import kr.co.maple.module.main.model.RankingDTO;
import kr.co.maple.module.main.model.RankingListDTO;
import kr.co.maple.module.main.model.Top10RankingListDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
    private final WebClientService webClientService;
    @Autowired
    private CommonParamsComponent commonParamsComponent;
	@Value("${maple.apiKey}")
	private String API_KEY;
	@Value("${maple.url}")
	private String BASE_URL;
	
    @PreDestroy
    @Cacheable("characterRankingCache")
    public HttpEntity<ResDTO<Top10RankingListDTO>> characterRankingOverall() {
    	// Top10 랭킹 정보
        List<RankingDTO> baseTop10Rankings = getRankingList("0");
        List<RankingDTO> rebootTop10Rankings = getRankingList("1");
        List<DojangRankingDTO> dojangTop10Rankings = getDojangRankingList();
        // 랭킹 1위 ocid
        CharacterIdDTO baseTop1OCID = getCharacterId(baseTop10Rankings.get(0).getCharacterName());
        // 랭킹 1위 캐릭터 기본 정보
        CharacterBasicDTO characterBasic = getCharacterBasic(baseTop1OCID.getOcid());
           
        log.info("값 --> " + characterBasic);
        Top10RankingListDTO top10RankingListDTO = Top10RankingListDTO.builder()
                .baseRankings(baseTop10Rankings)
                .rebootRankings(rebootTop10Rankings)
                .dojangRankings(dojangTop10Rankings)
                .build();
        log.info("Top10 랭킹 리스트 --> " + top10RankingListDTO);		
        return new ResponseEntity<>(
                ResDTO.<Top10RankingListDTO>builder()
                        .code(0)
                        .data(top10RankingListDTO)
                        .message("캐릭터 랭킹 정보를 불러왔습니다.")
                        .build(),
                        HttpStatus.OK
        );
    }
    // 일반월드, 리부트월드 Top10 랭킹 리스트
    private List<RankingDTO> getRankingList(String worldType) {
        MultiValueMap<String, String> params = commonParamsComponent.mapleRankingCommonParams("2023-12-31", worldType, "");
        RankingListDTO rankingDTOList = webClientService.webClientGetApi(
                BASE_URL + "/maplestory/v1/ranking/overall",
                params,
                API_KEY,
                RankingListDTO.class
        );
        return rankingDTOList.getRanking().subList(0, Math.min(rankingDTOList.getRanking().size(), 10));
    }
    // 무릉도장 Top10 랭킹 리스트
    private List<DojangRankingDTO> getDojangRankingList() {
        MultiValueMap<String, String> params = commonParamsComponent.mapleRankingCommonParams("2024-01-02", "", "1");
        DojangRankingListDTO rankingDTOList = webClientService.webClientGetApi(
                BASE_URL + "/maplestory/v1/ranking/dojang",
                params,
                API_KEY,
                DojangRankingListDTO.class
        );
        return rankingDTOList.getRanking().subList(0, Math.min(rankingDTOList.getRanking().size(), 10));
    }
    // 캐릭터 식별자 조회
    private CharacterIdDTO getCharacterId(String characterName) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleCharacterCommonParams(characterName);
    	CharacterIdDTO characterIdDTO = webClientService.webClientGetApi(
                BASE_URL + "/maplestory/v1/id",
                params,
                API_KEY,
                CharacterIdDTO.class
        );
    	return characterIdDTO;
    }
    // 캐릭터 기본 정보 조회
    private CharacterBasicDTO getCharacterBasic(String ocid) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleCharacterBasicCommonParams(ocid, "2024-01-02");
		CharacterBasicDTO characterBasicDTO = webClientService.webClientGetApi(
				BASE_URL + "/maplestory/v1/character/basic",
				params,
				API_KEY,
				CharacterBasicDTO.class
		); 
		return characterBasicDTO;
    }    
}