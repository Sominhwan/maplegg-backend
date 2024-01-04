package kr.co.maple.module.main.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Top1DojangRankingDTO {
	private String characterName;
	private String worldName;
	private int characterLevel;
	private String characterClass;
	private int dojangFloor;
	private int dojangTimeRecord;
	private String characterImage;
}
