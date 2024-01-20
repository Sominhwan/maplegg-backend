package kr.co.maple.module.characterInfo.model;

import kr.co.maple.module.main.model.CharacterBasicDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterInfoDTO {
	CharacterBasicDTO characterBasicInfo;
	CharacterPopularityDTO characterPopularityInfo;
	CharacterStatDTO characterStatInfo;
}
