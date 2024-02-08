package kr.co.maple.module.characterInfo.model;

import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.RankingDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterInfoDTO {
	CharacterBasicDTO characterBasicInfo;
	CharacterPopularityDTO characterPopularityInfo;
	CharacterStatDTO characterStatInfo;
	RankingDTO characterOverallRanking;
	RankingDTO characterWorldRanking;
	RankingDTO characterWorldClassRanking;
	RankingDTO characterTotalClassRanking;
}
