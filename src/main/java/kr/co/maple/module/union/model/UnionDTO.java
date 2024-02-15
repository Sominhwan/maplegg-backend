package kr.co.maple.module.union.model;

import lombok.Data;

@Data
public class UnionDTO {
	private String date;
	private int union_level;
	private String union_grade;
	private int union_artifact_level;
	private int union_artifact_exp;
	private int union_artifact_point;
}
