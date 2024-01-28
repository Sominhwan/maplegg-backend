package kr.co.maple.module.statAndEquip.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CharacterAndroidEquipmentDTO {
	private String date;
	@JsonProperty("android_name")
	private String androidName;
	@JsonProperty("android_nickname")
	private String androidNickname;
	@JsonProperty("android_icon")
	private String androidIcon;
	@JsonProperty("android_description")
	private String androidDescription;
	@JsonProperty("android_hair")
	private AndroidHair androidHair;
	@JsonProperty("android_face")
	private AndroidFace androidFace;
	@JsonProperty("android_skin_name")
	private String androidSkinName;
	@JsonProperty("android_cash_item_equipment")
	private List<AndroidCashItemEquipment> androidCashItemEquipment;
	@JsonProperty("android_ear_sensor_clip_flag")
	private String androidEarSensorClipFlag;
	@JsonProperty("android_gender")
	private String androidGender;
	@JsonProperty("android_grade")
	private String androidGrade;
	@JsonProperty("android_non_humanoid_flag")
	private String androidNonHumanoidFlag;
	@JsonProperty("android_shop_usable_flag")
	private String androidShopUsableFlag;
	@JsonProperty("preset_no")
	private String presetNo;
	@JsonProperty("android_preset_1")
	private AndroidPreset1 androidPreset1;
	@JsonProperty("android_preset_2")
	private AndroidPreset2 androidPreset2;
	@JsonProperty("android_preset_3")
	private AndroidPreset3 androidPreset3;
	
	@Data
	private static class AndroidHair {
		@JsonProperty("hair_name")
		private String hairName;
		@JsonProperty("base_color")
		private String baseColor;
		@JsonProperty("mix_color")
		private String mixColor;
		@JsonProperty("mix_rate")
		private String mixRate;
	}
	@Data
	private static class AndroidFace {
		@JsonProperty("face_name")
		private String faceName;
		@JsonProperty("base_color")
		private String baseColor;
		@JsonProperty("mix_color")
		private String mixColor;
		@JsonProperty("mix_rate")
		private String mixRate;
	}
	@Data
	private static class AndroidCashItemEquipment {
		@JsonProperty("cash_item_equipment_part")
		private String cashItemEquipmentPart;
		@JsonProperty("cash_item_equipment_slot")
		private String cashItemEquipmentSlot;
		@JsonProperty("cash_item_name")
		private String cashItemName;
		@JsonProperty("cash_item_icon")
		private String cashItemIcon;
		@JsonProperty("cash_item_description")
		private String cashItemDescription;
		@JsonProperty("cash_item_option")
		private List<CashItemOption> cashItemOption;
		@JsonProperty("date_expire")
		private String dateExpire;
		@JsonProperty("date_option_expire")
		private String dateOptionExpire;
		@JsonProperty("cash_item_label")
		private String cashItemLabel;
		@JsonProperty("cash_item_coloring_prism")
		private CashItemColoringPrism cashItemColoringPrism;
		@JsonProperty("android_item_gender")
		private String androidItemGender;
	}
	@Data
	private static class CashItemOption {
		@JsonProperty("option_type")
		private String optionType;
		@JsonProperty("option_value")
		private String optionValue;
	}
	@Data
	private static class CashItemColoringPrism {
		@JsonProperty("color_range")
		private String colorRange;
		private int hue;
		private int saturation;
		private int value;
	}
	@Data
	private static class AndroidPreset1 {
		@JsonProperty("android_name")
		private String androidName;
		@JsonProperty("android_nickname")
		private String androidNickname;
		@JsonProperty("android_icon")
		private String androidIcon;
		@JsonProperty("android_description")
		private String androidDescription;
		@JsonProperty("android_gender")
		private String androidGender;
		@JsonProperty("android_grade")
		private String androidGrade;
		@JsonProperty("android_skin_name")
		private String androidSkinName;
		@JsonProperty("android_hair")
		private AndroidHair androidHair;
		@JsonProperty("android_face")
		private AndroidFace androidFace;
		@JsonProperty("android_ear_sensor_clip_flag")
		private String androidEarSensorClipFlag;
		@JsonProperty("android_non_humanoid_flag")
		private String androidNonHumanoidFlag;
		@JsonProperty("android_shop_usable_flag")
		private String androidShopUsableFlag;
	}
	@Data
	private static class AndroidPreset2 {
		@JsonProperty("android_name")
		private String androidName;
		@JsonProperty("android_nickname")
		private String androidNickname;
		@JsonProperty("android_icon")
		private String androidIcon;
		@JsonProperty("android_description")
		private String androidDescription;
		@JsonProperty("android_gender")
		private String androidGender;
		@JsonProperty("android_grade")
		private String androidGrade;
		@JsonProperty("android_skin_name")
		private String androidSkinName;
		@JsonProperty("android_hair")
		private AndroidHair androidHair;
		@JsonProperty("android_face")
		private AndroidFace androidFace;
		@JsonProperty("android_ear_sensor_clip_flag")
		private String androidEarSensorClipFlag;
		@JsonProperty("android_non_humanoid_flag")
		private String androidNonHumanoidFlag;
		@JsonProperty("android_shop_usable_flag")
		private String androidShopUsableFlag;
	}
	@Data
	private static class AndroidPreset3 {
		@JsonProperty("android_name")
		private String androidName;
		@JsonProperty("android_nickname")
		private String androidNickname;
		@JsonProperty("android_icon")
		private String androidIcon;
		@JsonProperty("android_description")
		private String androidDescription;
		@JsonProperty("android_gender")
		private String androidGender;
		@JsonProperty("android_grade")
		private String androidGrade;
		@JsonProperty("android_skin_name")
		private String androidSkinName;
		@JsonProperty("android_hair")
		private AndroidHair androidHair;
		@JsonProperty("android_face")
		private AndroidFace androidFace;
		@JsonProperty("android_ear_sensor_clip_flag")
		private String androidEarSensorClipFlag;
		@JsonProperty("android_non_humanoid_flag")
		private String androidNonHumanoidFlag;
		@JsonProperty("android_shop_usable_flag")
		private String androidShopUsableFlag;
	}
}
