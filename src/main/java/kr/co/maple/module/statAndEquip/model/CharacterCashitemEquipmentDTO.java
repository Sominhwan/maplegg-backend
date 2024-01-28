package kr.co.maple.module.statAndEquip.model;

import java.util.List;

import lombok.Data;

@Data
public class CharacterCashitemEquipmentDTO {
	private String date;
	private String character_gender;
	private String character_class;
	private int preset_no;
	private List<CashItemEquipmentBase> cash_item_equipment_base;
	private List<CashItemEquipmentPreset1> cash_item_equipment_preset_1;
	private List<CashItemEquipmentPreset2> cash_item_equipment_preset_2;
	private List<CashItemEquipmentPreset3> cash_item_equipment_preset_3;
	private List<AdditionalCashItemEquipmentBase> additional_cash_item_equipment_base;
	private List<AdditionalCashItemEquipmentPreset1> additional_cash_item_equipment_preset_1;
	private List<AdditionalCashItemEquipmentPreset2> additional_cash_item_equipment_preset_2;
	private List<AdditionalCashItemEquipmentPreset3> additional_cash_item_equipment_preset_3;
	
	@Data
	private static class CashItemEquipmentBase {
		private String cash_item_equipment_part;
		private String cash_item_equipment_slot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
	@Data
	private static class CashItemOption {
		private String option_type;
		private String option_value;
	}
	@Data
	private static class CashItemColoringPrism {
		private String color_range;
		private int hue;
		private int saturation;
		private int value;
	}
	@Data
	private static class CashItemEquipmentPreset1 {
		private String cash_item_equipment_part;
		private String cash_item_equipment_solot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
	@Data
	private static class CashItemEquipmentPreset2 {
		private String cash_item_equipment_part;
		private String cash_item_equipment_solot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
	@Data
	private static class CashItemEquipmentPreset3 {
		private String cash_item_equipment_part;
		private String cash_item_equipment_solot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
	@Data
	private static class AdditionalCashItemEquipmentBase {
		private String cash_item_equipment_part;
		private String cash_item_equipment_slot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;	
	}
	@Data
	private static class AdditionalCashItemEquipmentPreset1 {
		private String cash_item_equipment_part;
		private String cash_item_equipment_slot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
	@Data
	private static class AdditionalCashItemEquipmentPreset2 {
		private String cash_item_equipment_part;
		private String cash_item_equipment_slot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
	@Data
	private static class AdditionalCashItemEquipmentPreset3 {
		private String cash_item_equipment_part;
		private String cash_item_equipment_slot;
		private String cash_item_name;
		private String cash_item_icon;
		private String cash_item_description;
		private List<CashItemOption> cash_item_option;
		private String date_expire;
		private String date_option_expire;
		private String cash_item_label;
		private CashItemColoringPrism cash_item_coloring_prism;
		private String item_gender;
	}
}
