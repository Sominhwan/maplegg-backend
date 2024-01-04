package kr.co.maple.module.main.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Top1AchievementRankingDTO {
	private String characterName;
	private String worldName;
	private int characterLevel;
	private String characterClass;
	private String trophyGrade;
	private int trophyScore;
	private String characterImage;
}
