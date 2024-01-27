package kr.co.maple.module.statAndEquip.model;

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
//	@JsonProperty("android_cash_item_equipment")
//	private List<AndroidCashItemEquipment> androidCashItemEquipment;
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
//	@JsonProperty("android_preset_1")
//	private AndroidPreset1 androidPreset1;
//	@JsonProperty("android_preset_2")
//	private AndroidPreset2 androidPreset2;
//	@JsonProperty("android_preset_3")
//	private AndroidPreset3 androidPreset3;
	
	@Data
	private static class AndroidHair {
		private String hairName;
		private String baseColor;
		private String mixColor;
		private String mixRate;
	}
	@Data
	private static class AndroidFace {
		private String faceName;
		private String baseColor;
		private String mixColor;
		private String mixRate;
	}
	@Data
	private static class AndroidCashItemEquipment {
		
	}
	@Data
	private static class AndroidPreset1 {
		
	}
	@Data
	private static class AndroidPreset2 {
		
	}
	@Data
	private static class AndroidPreset3 {
		
	}
}
