package kr.co.maple.module.skillAndSymbol.model;

import java.util.List;

import lombok.Data;

@Data
public class HexamatrixStatDTO {
	private String date;
	private String character_class;
	private List<CharacterHexaStatCore> character_hexa_stat_core;
	private List<PresetHexaStatCore> preset_hexa_stat_core;
	
	@Data
	private static class CharacterHexaStatCore {
		private String slot_id;
		private String main_stat_name;
		private String sub_stat_name_1;
		private String sub_stat_name_2;
		private int main_stat_level;
		private int sub_stat_level_1;
		private int sub_stat_level_2;
		private int stat_grade;
	}
	@Data
	private static class PresetHexaStatCore {
		private String slot_id;
		private String main_stat_name;
		private String sub_stat_name_1;
		private String sub_stat_name_2;
		private int main_stat_level;
		private int sub_stat_level_1;
		private int sub_stat_level_2;
		private int stat_grade;
	}
}
