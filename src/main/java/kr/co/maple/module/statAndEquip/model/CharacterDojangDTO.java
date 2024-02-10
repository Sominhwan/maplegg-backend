package kr.co.maple.module.statAndEquip.model;

import lombok.Data;

@Data
public class CharacterDojangDTO {
	private String date;
	private String character_class;
	private String world_name;
	private int dojang_best_floor;
	private String date_dojang_record;
	private int dojang_best_time;
}
