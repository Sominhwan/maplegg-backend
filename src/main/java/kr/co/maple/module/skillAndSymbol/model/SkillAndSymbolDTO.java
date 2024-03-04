package kr.co.maple.module.skillAndSymbol.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillAndSymbolDTO {
	private HexamatrixDTO hexamatrix;
	private HexamatrixStatDTO hexamatrixStat;
	private VmatrixDTO vmatrix;
}
