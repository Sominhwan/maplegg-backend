package kr.co.maple.module.skillAndSymbol.model;

import java.util.List;

import lombok.Data;

@Data
public class HexamatrixDTO {
	private String date;
	private List<CharacterHexaCoreEquipment> character_hexa_core_equipment;
	@Data
	private static class CharacterHexaCoreEquipment {
		private String hexa_core_name;
		private int hexa_core_level;
		private String hexa_core_type;
		List<LinkedSkill> linked_skill;
	}
	@Data
	private static class LinkedSkill {
		private String hexa_skill_id;
	}
}
