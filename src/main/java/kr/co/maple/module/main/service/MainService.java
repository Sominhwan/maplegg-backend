package kr.co.maple.module.main.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import kr.co.maple.common.model.ResDTO;
import kr.co.maple.common.service.WebClientService;
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
	private final WebClientService webClientServce;
	@Value("${maple.apiKey}")
	private String API_KEY;
	@Value("${maple.url}")
	private String BASE_URL;

	@Cacheable("characterRankingCache")
	public HttpEntity<?> characterRankingOverall() {
	    // String characterName = URLEncoder.encode("법사로키우기", StandardCharsets.UTF_8);
		// 일반월드 Top10 랭킹
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("date", "2023-12-31");
	    params.add("world_type", "0");
	       
	    RankingListDTO rankingDTOList  = 
	    		webClientServce.webClientGetApi(BASE_URL + "/maplestory/v1/ranking/overall", params, API_KEY, RankingListDTO.class);
	    List<RankingDTO> rankingList = rankingDTOList.getRanking();
	    List<RankingDTO> baseTop10Rankings = rankingList.subList(0, Math.min(rankingList.size(), 10));
		// 리부트월드 Top10 랭킹
	    MultiValueMap<String, String> params2 = new LinkedMultiValueMap<String, String>();
	    params2.add("date", "2023-12-31");
	    params2.add("world_type", "1");
	    
	    RankingListDTO rankingDTOList2  = 
	    		webClientServce.webClientGetApi(BASE_URL + "/maplestory/v1/ranking/overall", params2, API_KEY, RankingListDTO.class);
	    List<RankingDTO> rankingList2 = rankingDTOList2.getRanking();
	    List<RankingDTO> rebootTop10Rankings = rankingList2.subList(0, Math.min(rankingList2.size(), 10));
	    // 무릉도장 Top10 랭킹
	    MultiValueMap<String, String> params3 = new LinkedMultiValueMap<String, String>();
	    params3.add("date", "2024-01-02");
	    params3.add("difficulty", "1");
	    
	    DojangRankingListDTO rankingDTOList3  = 
	    		webClientServce.webClientGetApi(BASE_URL + "/maplestory/v1/ranking/dojang", params3, API_KEY, DojangRankingListDTO.class);
	    List<DojangRankingDTO> rankingList3 = rankingDTOList3.getRanking();
	    List<DojangRankingDTO> dojangTop10Rankings = rankingList3.subList(0, Math.min(rankingList3.size(), 10));
	    
	    Top10RankingListDTO top10RankingListDTO = Top10RankingListDTO.builder()
													.baseRankings(baseTop10Rankings)
													.rebootRankings(rebootTop10Rankings)
													.dojangRankings(dojangTop10Rankings)
													.build();
		
		log.info("Top10 랭킹 리스트 --> " + top10RankingListDTO);				
		return new ResponseEntity<>(
				ResDTO.builder()
						.code(0)
						.data(top10RankingListDTO)
						.message("캐릭터 랭킹 정보를 불러왔습니다.")
						.build(),
						HttpStatus.OK);			
	}
}
