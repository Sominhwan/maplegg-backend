package kr.co.maple.module.union.model;

import java.util.List;

import lombok.Data;

@Data
public class UnionArtifactDTO {
	private String date;
	List<UnionArtifactEffect> union_artifact_effect;
	List<UnionArtifactCrystal> union_artifact_crystal;
	int union_artifact_remain_ap;
	
	@Data
	private static class UnionArtifactEffect  {
		private String name;
		private int level;
	}
	@Data
	private static class UnionArtifactCrystal {
		private String name;
		private String validity_flag;
		private String date_expire;
		private int level;
		private String crystal_option_name_1;
		private String crystal_option_name_2;
		private String crystal_option_name_3;
	}
}
