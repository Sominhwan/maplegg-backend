package kr.co.maple.module.skillAndSymbol.model;

import java.util.List;

import lombok.Data;

@Data
public class VmatrixDTO {
	private String date;
	private String character_class;
	private List<CharacterVCoreEquipment> character_v_core_equipment;
	private int characater_v_matrix_remain_slot_upgrade_point;
	
	@Data
	private static class CharacterVCoreEquipment {
		private String slot_id;
		private int slot_level;
		private String v_core_name;
		private String v_core_type;
		private int v_core_level;
		private String v_core_skill_1;
		private String v_core_skill_2;
		private String v_core_skill_3;
	}
}
