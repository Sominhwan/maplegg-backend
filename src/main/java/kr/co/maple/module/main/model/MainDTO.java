package kr.co.maple.module.main.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainDTO {
	private List<RankingDTO> baseRankings;
	private List<RankingDTO> rebootRankings;
	private List<DojangRankingDTO> dojangRankings;
	private List<GuildRankingDTO> guildRankings;
	
	private Top1LevelRankingDTO top1LevelRanking;
	private Top1DojangRankingDTO top1DojangRanking;
	private Top1AchievementRankingDTO top1AchievementRanking;
	private Top1UnionRankingDTO top1UnionRanking;
}
