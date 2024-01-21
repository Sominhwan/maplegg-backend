package kr.co.maple.module.statAndEquip.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CharacterItemEquipmentDTO {
	private String date;
	@JsonProperty("character_gender")
	private String characterGender;
	@JsonProperty("character_class")
	private String characterClass;
	@JsonProperty("preset_no")
	private int presetNo;
	@JsonProperty("item_equipment")
	private List<ItemEquipment> itemEquipment;
	private Title title;
	@JsonProperty("dragon_equipment")
	private List<DragonEquipment> dragonEquipment;
	@JsonProperty("mechanic_equipment")
	private List<MechanicEquipment> mechanicEquipment;
	
	@Data
	private static class ItemEquipment {
		@JsonProperty("item_equipment_part")
		private String itemEquipmentPart;
		@JsonProperty("item_equipment_slot")
		private String itemEquipmentSlot;
		@JsonProperty("item_name")
		private String itemName;
		@JsonProperty("item_icon")
		private String itemIcon;
		@JsonProperty("item_description")
		private String itemDescription;
		@JsonProperty("item_shape_name")
		private String itemShapeName;
		@JsonProperty("item_shape_icon")
		private String itemShapeIcon;
		@JsonProperty("item_gender")
		private String itemGender;
		@JsonProperty("item_total_option")
		private ItemTotalOption itemTotalOption;
		@JsonProperty("item_base_option")
		private ItemBaseOption itemBaseOption;
		@JsonProperty("potential_option_grade")
		private String potentialOptionGrade;
		@JsonProperty("additional_potential_option_grade")
		private String additionalPotentialOptionGrade;
		@JsonProperty("potential_option_1")
		private String potentialOption1;
		@JsonProperty("potential_option_2")
		private String potentialOption2;
		@JsonProperty("potential_option_3")
		private String potentialOption3;
		@JsonProperty("additional_potential_option_1")
		private String additionalPotentialOption1;
		@JsonProperty("additional_potential_option_2")
		private String additionalPotentialOption2;
		@JsonProperty("additional_potential_option_3")
		private String additionalPotentialOption3;
		@JsonProperty("equipment_level_increase")
		private int equipmentLevelIncrease;
		@JsonProperty("item_exceptional_option")
		private ItemExceptionalOption itemExceptionalOption;
		@JsonProperty("item_add_option")
		private ItemAddOption itemAddOption;
		@JsonProperty("growth_exp")
		private long growthExp;
		@JsonProperty("growth_level")
		private int growthLevel;
		@JsonProperty("scroll_upgrade")
		private String scrollUpgrade;
		@JsonProperty("cuttable_count")
		private String cuttableCount;
		@JsonProperty("golden_hammer_flag")
		private String goldenHammerFlag;
		@JsonProperty("scroll_resilience_count")
		private String scrollResilienceCount;
		@JsonProperty("scroll_upgradeable_count")
		private String scrollUpgradeableCount;
		@JsonProperty("soul_name")
		private String soulName;
		@JsonProperty("soul_option")
		private String soulOption;
		@JsonProperty("item_etc_option")
		private ItemEtcOption itemEtcOption;
		@JsonProperty("starforce")
		private String starforce;
		@JsonProperty("starforce_scroll_flag")
		private String starforceScrollFlag;
		@JsonProperty("item_starforce_option")
		private ItemStarForceOption itemStarforceOption;
		@JsonProperty("special_ring_level")
		private int specialRingLevel;
		@JsonProperty("date_expire")
		private String dateExpire;			
	}
	
	@Data
	private static class ItemTotalOption {
		private String str;
		private String dex;
		@JsonProperty("int")
		private String int2;
		private String luk;
		@JsonProperty("max_hp")
		private String maxHp;
		@JsonProperty("max_mp")
		private String maxMp;
		@JsonProperty("attack_power")
		private String attackPower;
		@JsonProperty("magic_power")
		private String magicPower;
		private String armor;
		private String speed;
		private String jump;
		@JsonProperty("boss_damage")
		private String bossDamage;
		@JsonProperty("ignore_monster_armor")
		private String ignoreMonsterArmor;
		@JsonProperty("all_stat")
		private String allStat;
		private String damage;
		@JsonProperty("equipment_level_decrease")
		private String equipmentLevelDecrease;
		@JsonProperty("max_hp_rate")
		private String maxHpRate;
		@JsonProperty("max_mp_rate")
		private String maxMpRate;	
	}
	
	@Data 
	private static class ItemBaseOption {
		private String str;
		private String dex;
		@JsonProperty("int")
		private String int2;
		private String luk;
		@JsonProperty("max_hp")
		private String maxHp;
		@JsonProperty("max_mp")
		private String maxMp;
		@JsonProperty("attack_power")
		private String attackPower;
		@JsonProperty("magic_power")
		private String magicPower;
		private String armor;
		private String speed;
		private String jump;
		@JsonProperty("boss_damage")
		private String bossDamage;
		@JsonProperty("ignore_monster_armor")
		private String ignoreMonsterArmor;
		@JsonProperty("all_stat")
		private String allStat;
		@JsonProperty("max_hp_rate")
		private String maxHpRate;
		@JsonProperty("max_mp_rate")
		private String maxMpRate;
		@JsonProperty("base_equipment_level")
		private String baseEquipmentLevel;
	}
	
	@Data
	private static class ItemExceptionalOption {
		private String str;
		private String dex;
		@JsonProperty("int")
		private String int2;
		private String luk;
		@JsonProperty("max_hp")
		private String maxHp;
		@JsonProperty("max_mp")
		private String maxMp;
		@JsonProperty("attack_power")
		private String attackPower;
		@JsonProperty("magic_power")
		private String magicPower;
	}
	
	@Data
	private static class ItemAddOption {
		private String str;
		private String dex;
		@JsonProperty("int")
		private String int2;
		private String luk;
		@JsonProperty("max_hp")
		private String maxHp;
		@JsonProperty("max_mp")
		private String maxMp;
		@JsonProperty("attack_power")
		private String attackPower;
		@JsonProperty("magic_power")
		private String magicPower;
		private String armor;
		private String speed;
		private String jump;
		@JsonProperty("boss_damage")
		private String bossDamage;
		private String damage;
		@JsonProperty("all_stat")
		private String allStat;
		@JsonProperty("equipment_level_decrease")
		private String equipmentLevelDecrease;

	}
	
	@Data
	private static class ItemEtcOption {
		private String str;
		private String dex;
		@JsonProperty("int")
		private String int2;
		private String luk;
		@JsonProperty("max_hp")
		private String maxHp;
		@JsonProperty("max_mp")
		private String maxMp;
		@JsonProperty("attack_power")
		private String attackPower;
		@JsonProperty("magic_power")
		private String magicPower;
		private String armor;
		private String speed;
		private String jump;
	}
	
	@Data
	private static class ItemStarForceOption {
		private String str;
		private String dex;
		@JsonProperty("int")
		private String int2;
		private String luk;
		@JsonProperty("max_hp")
		private String maxHp;
		@JsonProperty("max_mp")
		private String maxMp;
		@JsonProperty("attack_power")
		private String attackPower;
		@JsonProperty("magic_power")
		private String magicPower;
		private String armor;
		private String speed;
		private String jump;
	}
	
	@Data
	private static class Title {
		@JsonProperty("title_name")
		private String titleName;
		@JsonProperty("title_icon")
		private String titleIcon;
		@JsonProperty("title_description")
		private String titleDescription;
		@JsonProperty("date_expire")
		private String dateExpire;
		@JsonProperty("date_option_expire")
		private String dateOptionExpire;
	}
	
	@Data
	private static class DragonEquipment {
		
	}
	
	@Data
	private static class MechanicEquipment {
		
	}
}
