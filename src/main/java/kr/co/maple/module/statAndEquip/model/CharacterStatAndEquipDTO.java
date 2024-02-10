package kr.co.maple.module.statAndEquip.model;

import kr.co.maple.module.main.model.AchievementRankingDTO;
import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.TheseedRankingDTO;
import kr.co.maple.module.main.model.UnionRankingDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterStatAndEquipDTO {
	private CharacterBasicDTO characterBasic;
	private CharacterItemEquipmentDTO characterItemEquipment;
	private CharacterAndroidEquipmentDTO characterAndroidEquipment;
	private CharacterCashitemEquipmentDTO characterCashitemEquipment;
	private CharacterPetEquipmentDTO characterPetEquipment;
	
	private CharacterDojangDTO characterDojangRanking;
	private TheseedRankingDTO characterTheseedRanking;
	private AchievementRankingDTO characterAchievementRanking;
	private UnionRankingDTO characterUnionRanking;
}
