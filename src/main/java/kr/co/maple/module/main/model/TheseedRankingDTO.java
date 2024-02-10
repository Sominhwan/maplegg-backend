package kr.co.maple.module.main.model;

import lombok.Data;

@Data
public class TheseedRankingDTO {
	private String date;
	private int ranking;
	private String character_name;
	private String world_name;
	private String class_name;
	private String sub_class_name;
	private int character_level;
	private int theseed_floor;
	private int theseed_time_record;
}
