package kr.co.maple.common.component;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class CommonParamsComponent {
	// 넥슨 Maple Api 랭킹 조회 Common Params 
    public MultiValueMap<String, String> mapleRankingCommonParams(String date, String worldType, String difficulty) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        params.add("world_type", worldType);
        params.add("difficulty", difficulty);
        return params;
    }
    // 넥슨 Maple Api 업적 랭킹 조회 Common Params
    public MultiValueMap<String, String> mapleAchievementRankingCommonParams(String date, String ocid, String page) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        params.add("ocid", ocid);
        params.add("page", page);
        return params;
    }
    // 넥슨 Maple Api 유니온 랭킹 조회 Common Params
    public MultiValueMap<String, String> mapleUnionRankingCommonParams(String date, String worldName, String ocid, String page) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date);
        params.add("world_name", worldName);
        params.add("ocid", ocid);
        params.add("page", page);
        return params;
    }
	// 넥슨 Maple Api 캐릭터 ocid 조회 Common Params 
    public MultiValueMap<String, String> mapleCharacterCommonParams(String characterName) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("character_name", characterName);
        return params;
    }
	// 넥슨 Maple Api 캐릭터 기본 정보 조회 Common Params 
    public MultiValueMap<String, String> mapleCharacterBasicCommonParams(String ocid, String date) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ocid", ocid);
        params.add("date", date);
        return params;
    }
}
