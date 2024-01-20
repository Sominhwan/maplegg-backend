package kr.co.maple.module.characterInfo.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FinalStatDTO {
	@JsonProperty("stat_name")
	private String statName;
	@JsonProperty("stat_value")
	private BigDecimal statValue;
}
