package kr.co.maple.module.main.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Top10RankingListDTO {
	private List<RankingDTO> baseRankings;
	private List<RankingDTO> rebootRankings;
	private List<DojangRankingDTO> dojangRankings;
}
