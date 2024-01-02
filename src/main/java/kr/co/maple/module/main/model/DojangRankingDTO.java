package kr.co.maple.module.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DojangRankingDTO {
    private String date;
    private int ranking;
    @JsonProperty("character_name")
    private String characterName;
    @JsonProperty("world_name")
    private String worldName;
    @JsonProperty("class_name")
    private String className;
    @JsonProperty("sub_class_name")
    private String subClassName;
    @JsonProperty("character_level")
    private String characterLevel;
    @JsonProperty("dojang_floor")
    private int dojangFloor;
    @JsonProperty("dojang_time_record")
    private int dojangTimeRecord;
    
}
