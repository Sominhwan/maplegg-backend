package kr.co.maple.module.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GuildRankingDTO {
	private String date;
	private int ranking;
	@JsonProperty("guild_name")
	private String guildName;
	@JsonProperty("world_name")
	private String worldName;
	@JsonProperty("guild_level")
	private int guildLevel;
    @JsonProperty("guild_master_name")
	private String guildMasterName;
    @JsonProperty("guild_mark")
	private String guildMark;
    @JsonProperty("guild_point")
	private int guildPoint;
}
