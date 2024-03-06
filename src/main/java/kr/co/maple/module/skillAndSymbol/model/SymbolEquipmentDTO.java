package kr.co.maple.module.skillAndSymbol.model;

import java.util.List;

import lombok.Data;

@Data
public class SymbolEquipmentDTO {
	private String date;
	private String character_class;
	private List<Symbol> symbol;
	
	@Data
	private static class Symbol {
		private String symbol_name;
		private String symbol_icon;
		private String symbol_description;
		private String symbol_force;
		private int symbol_level;
		private String symbol_str;
		private String symbol_dex;
		private String symbol_int;
		private String symbol_luk;
		private String symbol_hp;
		private int symbol_growth_count;
		private int symbol_require_growth_count;
	}
}
