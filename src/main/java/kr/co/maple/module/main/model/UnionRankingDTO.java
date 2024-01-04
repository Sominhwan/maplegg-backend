package kr.co.maple.module.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnionRankingDTO {
	private String date;
	private int ranking;
    @JsonProperty("character_name")
	private String characterName;
    @JsonProperty("world_name")
	private String worldName;
    @JsonProperty("class_name")
	private String className;
    @JsonProperty("sub_class_name")
	private String subClassName;
    @JsonProperty("union_level")
	private int unionLevel;
    @JsonProperty("union_power")
	private long unionPower;
}
