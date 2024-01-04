package kr.co.maple.module.main.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Top1UnionRankingDTO {
	private String characterName;
	private String worldName;
	private int characterLevel;
	private String characterClass;
	private int unionLevel;
	private int unionPower;
	private String characterImage;
}
