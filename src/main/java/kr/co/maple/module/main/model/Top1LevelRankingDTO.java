package kr.co.maple.module.main.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Top1LevelRankingDTO {
	private String characterName;
	private String worldName;
	private int characterLevel;
	private String characterClass;
	private long characterExp;
	private String characterImage;
}
