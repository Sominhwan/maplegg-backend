package kr.co.maple.module.characterInfo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CharacterStatDTO {
	@JsonProperty("character_class")
	private String characterClass;
	@JsonProperty("final_stat")
	private List<FinalStatDTO> finalStat;
}
