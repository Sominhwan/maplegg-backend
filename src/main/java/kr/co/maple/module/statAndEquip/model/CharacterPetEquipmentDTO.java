package kr.co.maple.module.statAndEquip.model;

import java.util.List;

import lombok.Data;

@Data
public class CharacterPetEquipmentDTO {
	private String date;
	private String pet_1_name;
	private String pet_1_nickname;
	private String pet_1_icon;
	private String pet_1_description;
	private Pet1Equipment pet_1_equipment;
	private Pet1AutoSkill pet_1_auto_skill;
	private String pet_1_pet_type;
	private List<String> pet_1_skill;
	private String pet_1_date_expire;
	private String pet_1_appearance;
	private String pet_1_appearance_icon;
	private String pet_2_name;
	private String pet_2_nickname;
	private String pet_2_icon;
	private String pet_2_description;
	private Pet2Equipment pet_2_equipment;
	private Pet2AutoSkill pet_2_auto_skill;
	private String pet_2_pet_type;
	private List<String> pet_2_skill;
	private String pet_2_date_expire;
	private String pet_2_appearance;
	private String pet_2_appearance_icon;
	private String pet_3_name;
	private String pet_3_nickname;
	private String pet_3_icon;
	private String pet_3_description;
	private Pet3Equipment pet_3_equipment;
	private Pet3AutoSkill pet_3_auto_skill;
	private String pet_3_pet_type;
	private List<String> pet_3_skill;
	private String pet_3_date_expire;
	private String pet_3_appearance;
	private String pet_3_appearance_icon;
	
	@Data
	private static class Pet1Equipment {
		private String item_name;
		private String item_icon;
		private String item_description;
		private List<ItemOption> item_option;
		private int scroll_upgrade;
		private int scroll_upgradable;
		private String item_shape;
		private String item_shape_icon;
	}
	@Data
	private static class ItemOption {
		private String option_type;
		private String option_value;
	}
	@Data
	private static class Pet1AutoSkill{
		private String skill_1;
		private String skill_1_icon;
		private String skill_2;
		private String skill_2_icon;
	}
	@Data
	private static class Pet2Equipment {
		private String item_name;
		private String item_icon;
		private String item_description;
		private List<ItemOption> item_option;
		private int scroll_upgrade;
		private int scroll_upgradable;
		private String item_shape;
		private String item_shape_icon;
	}
	@Data
	private static class Pet2AutoSkill {
		private String skill_1;
		private String skill_1_icon;
		private String skill_2;
		private String skill_2_icon;
	}
	@Data
	private static class Pet3Equipment {
		private String item_name;
		private String item_icon;
		private String item_description;
		private List<ItemOption> item_option;
		private int scroll_upgrade;
		private int scroll_upgradable;
		private String item_shape;
		private String item_shape_icon;
	}
	@Data
	private static class Pet3AutoSkill {
		private String skill_1;
		private String skill_1_icon;
		private String skill_2;
		private String skill_2_icon;
	}
}
