package kr.co.maple.common.component;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class CommonParamsComponent {
	// 넥슨 Maple Api 랭킹 조회 Common Params 
    public MultiValueMap<String, String> mapleRankingCommonParams(String date, String worldName, String worldType, String characterClass, String ocid, String difficulty) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        if(worldName != null) {
        	params.add("world_name", worldName);
        }
        if(worldType != null) {
        	params.add("world_type", worldType);
        }
        if(characterClass != null) {
        	params.add("class", characterClass);
        }
        if(ocid != null) {
        	params.add("ocid", ocid);
        }
        if(difficulty != null) {
            params.add("difficulty", difficulty);
        }
        return params;
    }
    // 넥슨 Maple Api 업적 랭킹 조회 Common Params
    public MultiValueMap<String, Object> mapleAchievementRankingCommonParams(String date, String ocid, String page) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        params.add("ocid", ocid);
        params.add("page", page);
        return params;
    }
    // 넥슨 Maple Api 유니온 랭킹 조회 Common Params
    public MultiValueMap<String, String> mapleUnionRankingCommonParams(String date, String worldName, String ocid, String page) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        if(worldName != null) {
            params.add("world_name", worldName);
        }
        if(ocid != null) {
            params.add("ocid", ocid);
        }
        if(page != null) {
            params.add("page", page);
        }
        return params;
    }
    // 넥슨 Maple Api 길드 랭킹 조회 Common Params(ranking_type = 0(주간 명성치), 1(플래그 레이스), 2(지하수로))
    public MultiValueMap<String, String> mapleGuildRankingCommonParams(String date, String rankingType) {
    	 MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
         params.add("date", date);
         params.add("ranking_type", rankingType);
         return params;
    }
	// 넥슨 Maple Api 캐릭터 ocid 조회 Common Params 
    public MultiValueMap<String, String> mapleCharacterCommonParams(String characterName) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("character_name", characterName);
        return params;
    }
	// 넥슨 Maple Api 캐릭터 정보 조회 Common Params 
    public MultiValueMap<String, String> mapleCharacterBasicCommonParams(String ocid, String date) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ocid", ocid);
        params.add("date", date);
        return params;
    }
}
