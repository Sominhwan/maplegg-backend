package kr.co.maple.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import kr.co.maple.common.component.CommonParamsComponent;
import kr.co.maple.module.characterInfo.model.CharacterPopularityDTO;
import kr.co.maple.module.characterInfo.model.CharacterStatDTO;
import kr.co.maple.module.main.model.AchievementRankingDTO;
import kr.co.maple.module.main.model.AchievementRankingListDTO;
import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.CharacterIdDTO;
import kr.co.maple.module.main.model.DojangRankingDTO;
import kr.co.maple.module.main.model.DojangRankingListDTO;
import kr.co.maple.module.main.model.GuildRankingDTO;
import kr.co.maple.module.main.model.GuildRankingListDTO;
import kr.co.maple.module.main.model.RankingDTO;
import kr.co.maple.module.main.model.RankingListDTO;
import kr.co.maple.module.main.model.UnionRankingDTO;
import kr.co.maple.module.main.model.UnionRankingListDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapleCharacterApiService {
    private final WebClientService webClientService;
    @Autowired
    private CommonParamsComponent commonParamsComponent;
	@Value("${maple.apiKey}")
	private String API_KEY;
	@Value("${maple.url}")
	private String BASE_URL;
    
    // 일반월드, 리부트월드 Top10 랭킹 리스트
    public List<RankingDTO> getRankingList(String date, String worldType) {
        MultiValueMap<String, String> params = commonParamsComponent.mapleRankingCommonParams(date, worldType, "");
        RankingListDTO rankingDTOList = webClientService.webClientGetApi(
                BASE_URL + "/maplestory/v1/ranking/overall",
                params,
                "x-nxopen-api-key",
                API_KEY,
                RankingListDTO.class
        );
        return rankingDTOList.getRanking().subList(0, Math.min(rankingDTOList.getRanking().size(), 10));
    }
    // 무릉도장 Top10 랭킹 리스트
    public List<DojangRankingDTO> getDojangRankingList(String date) {
        MultiValueMap<String, String> params = commonParamsComponent.mapleRankingCommonParams(date, "", "1");
        DojangRankingListDTO rankingDTOList = webClientService.webClientGetApi(
                BASE_URL + "/maplestory/v1/ranking/dojang",
                params,
                "x-nxopen-api-key",
                API_KEY,
                DojangRankingListDTO.class
        );
        return rankingDTOList.getRanking().subList(0, Math.min(rankingDTOList.getRanking().size(), 10));
    }
    // 업적 Top10 랭킹 리스트
    public List<AchievementRankingDTO> getAchievementRankingList(String date) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleRankingCommonParams(date, "", "");
    	AchievementRankingListDTO achievementRankingDTOList = webClientService.webClientGetApi(
	            BASE_URL + "/maplestory/v1/ranking/achievement",
	            params,
                "x-nxopen-api-key",
	            API_KEY,
	            AchievementRankingListDTO.class
	    );
    	return achievementRankingDTOList.getRanking().subList(0, Math.min(achievementRankingDTOList.getRanking().size(), 10));
    }
    // 유니온 Top10 랭킹 리스트
    public List<UnionRankingDTO> getUnionRankingList(String date) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleUnionRankingCommonParams(date, "");
    	UnionRankingListDTO unionRankingDTOList = webClientService.webClientGetApi(
	            BASE_URL + "/maplestory/v1/ranking/union",
	            params,
                "x-nxopen-api-key",
	            API_KEY,
	            UnionRankingListDTO.class
	    );
    	return unionRankingDTOList.getRanking().subList(0, Math.min(unionRankingDTOList.getRanking().size(), 10));
    }
    // 길드 Top10 랭킹 리스트
    public List<GuildRankingDTO> getGuildRankingList(String date, String rankingType) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleGuildRankingCommonParams(date, rankingType);
    	GuildRankingListDTO guildRankingDTOList = webClientService.webClientGetApi(
    			BASE_URL + "/maplestory/v1/ranking/guild",
    			params,
    			"x-nxopen-api-key",
    			API_KEY,
    			GuildRankingListDTO.class
		);
    	return guildRankingDTOList.getRanking().subList(0, Math.min(guildRankingDTOList.getRanking().size(), 10));
    }
    // 캐릭터 식별자 조회
    public CharacterIdDTO getCharacterId(String characterName) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleCharacterCommonParams(characterName);
    	CharacterIdDTO characterIdDTO = webClientService.webClientGetApi(
                BASE_URL + "/maplestory/v1/id",
                params,
                "x-nxopen-api-key",
                API_KEY,
                CharacterIdDTO.class
        );
    	return characterIdDTO;
    }
    // 캐릭터 기본 정보 조회
    public CharacterBasicDTO getCharacterBasic(String date, String ocid) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleCharacterBasicCommonParams(ocid, date);
		CharacterBasicDTO characterBasicDTO = webClientService.webClientGetApi(
				BASE_URL + "/maplestory/v1/character/basic",
				params,
                "x-nxopen-api-key",
				API_KEY,
				CharacterBasicDTO.class
		); 
		return characterBasicDTO;
    }  
    // 캐릭터 종합 능력치 정보 조회
    public CharacterStatDTO getCharacterStat(String date, String ocid) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleCharacterBasicCommonParams(ocid, date);
    	CharacterStatDTO characterStatDTO = webClientService.webClientGetApi(
    			BASE_URL + "/maplestory/v1/character/stat",
    			params,
    			"x-nxopen-api-key",
    			API_KEY,
    			CharacterStatDTO.class
		);
    	return characterStatDTO;
    }
    // 캐릭터 인기도 정보 조회
    public CharacterPopularityDTO getCharacterPopularity(String date, String ocid) {
    	MultiValueMap<String, String> params = commonParamsComponent.mapleCharacterBasicCommonParams(ocid, date);
    	CharacterPopularityDTO characterPopularityDTO = webClientService.webClientGetApi(
    			BASE_URL + "/maplestory/v1/character/popularity",
    			params,
    			"x-nxopen-api-key",
    			API_KEY,
    			CharacterPopularityDTO.class
		);
    	return characterPopularityDTO;
    }
}
