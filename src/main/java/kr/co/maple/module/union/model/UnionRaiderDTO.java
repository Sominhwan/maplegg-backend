package kr.co.maple.module.union.model;

import java.util.List;

import lombok.Data;

@Data
public class UnionRaiderDTO {
	private String date;
	private List<String> union_raider_stat;
	private List<String> union_occupied_stat;
	private List<UnionInnerStat> union_inner_stat;
	private List<UnionBlock> union_block;
	
	@Data
	private static class UnionInnerStat {
		private String stat_field_id;
		private String stat_field_effect;
	}
	
	@Data
	private static class UnionBlock {
		private String block_type;
		private String block_class;
		private String block_level;
		private BlockControlPoint block_control_point;
		private List<BlockPosition> block_position;
	}
	
	@Data
	private static class BlockControlPoint {
		private int x;
		private int y;
	}
	
	@Data
	private static class BlockPosition {
		private int x;
		private int y;
	}
}
