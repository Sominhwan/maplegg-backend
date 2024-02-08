package kr.co.maple.module.main.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.maple.common.component.MapleApiTimeCheckComponent;
import kr.co.maple.common.model.ResDTO;
import kr.co.maple.common.service.MapleCharacterApiService;
import kr.co.maple.module.main.model.AchievementRankingDTO;
import kr.co.maple.module.main.model.CharacterBasicDTO;
import kr.co.maple.module.main.model.CharacterIdDTO;
import kr.co.maple.module.main.model.DojangRankingDTO;
import kr.co.maple.module.main.model.GuildRankingDTO;
import kr.co.maple.module.main.model.MainDTO;
import kr.co.maple.module.main.model.RankingDTO;
import kr.co.maple.module.main.model.Top1AchievementRankingDTO;
import kr.co.maple.module.main.model.Top1DojangRankingDTO;
import kr.co.maple.module.main.model.Top1LevelRankingDTO;
import kr.co.maple.module.main.model.Top1UnionRankingDTO;
import kr.co.maple.module.main.model.UnionRankingDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
    private final MapleCharacterApiService maleCharacterApiService;
    @Autowired
    private MapleApiTimeCheckComponent mapleApiTimeCheckComponent;
	@Value("${maple.apiKey}")
	private String API_KEY;
	@Value("${maple.url}")
	private String BASE_URL;
	
    @PreDestroy
    @Cacheable("characterRankingCache")
    public HttpEntity<ResDTO<MainDTO>> characterRankingOverall() {
    	// yyyy-mm-dd
        String currentDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 0);
        // yyyymmdd -> (yyyy-mm-dd) - 1day 
        String previousDate = mapleApiTimeCheckComponent.timeCheck(new Date(), 1);
    	// Top10 랭킹 정보
        List<RankingDTO> baseTop10Rankings = maleCharacterApiService.getRankingList(currentDate, null, "0", null, null);
        List<RankingDTO> rebootTop10Rankings = maleCharacterApiService.getRankingList(currentDate, null, "1", null, null);
        List<DojangRankingDTO> dojangTop10Rankings = maleCharacterApiService.getDojangRankingList(currentDate);
        List<AchievementRankingDTO> achievementTop10Rankings = maleCharacterApiService.getAchievementRankingList(currentDate);
        List<UnionRankingDTO> unionTop10Rankings = maleCharacterApiService.getUnionRankingList(currentDate);
        List<GuildRankingDTO> guildTop10Rankings = maleCharacterApiService.getGuildRankingList(currentDate, "2");
        // 랭킹 1위 ocid
        CharacterIdDTO baseTop1OCID = maleCharacterApiService.getCharacterId(baseTop10Rankings.get(0).getCharacterName());
        // 랭킹 1위 캐릭터 기본 정보
        CharacterBasicDTO characterBasicInfo = maleCharacterApiService.getCharacterBasic(previousDate, baseTop1OCID.getOcid());
        Top1LevelRankingDTO top1LevelRanking = Top1LevelRankingDTO.builder()
        		.characterName(characterBasicInfo.getCharacterName())
        		.worldName(characterBasicInfo.getWorldName())
        		.characterLevel(characterBasicInfo.getCharacterLevel())
        		.characterClass(characterBasicInfo.getCharacterClass())
        		.characterExp(characterBasicInfo.getCharacterExp())
        		.characterImage(characterBasicInfo.getCharacterImage())
        		.build();
        // 무릉도장 랭킹 1위 ocid
        CharacterIdDTO dojangTop1OCID = maleCharacterApiService.getCharacterId(dojangTop10Rankings.get(0).getCharacterName());
        // 무릉도장 랭킹 1위 캐릭터 기본 정보
        CharacterBasicDTO characterDojangInfo = maleCharacterApiService.getCharacterBasic(previousDate, dojangTop1OCID.getOcid());
        Top1DojangRankingDTO top1DojangRanking = Top1DojangRankingDTO.builder()
        		.characterName(characterDojangInfo.getCharacterName())
        		.worldName(characterDojangInfo.getWorldName())
        		.characterLevel(characterDojangInfo.getCharacterLevel())
        		.characterClass(characterDojangInfo.getCharacterClass())
        		.dojangFloor(dojangTop10Rankings.get(0).getDojangFloor())
        		.dojangTimeRecord(dojangTop10Rankings.get(0).getDojangTimeRecord())
        		.characterImage(characterDojangInfo.getCharacterImage())
        		.build();
        // 업적 랭킹 1위 ocid
        CharacterIdDTO acheivementTop1OCID = maleCharacterApiService.getCharacterId(achievementTop10Rankings.get(0).getCharacterName());
        // 업적 랭킹 1위 캐릭터 기본 정보
        CharacterBasicDTO characterAchievementInfo = maleCharacterApiService.getCharacterBasic(previousDate, acheivementTop1OCID.getOcid());
        Top1AchievementRankingDTO top1AchievementRanking = Top1AchievementRankingDTO.builder()
        		.characterName(characterAchievementInfo.getCharacterName())
        		.worldName(characterAchievementInfo.getWorldName())
        		.characterLevel(characterAchievementInfo.getCharacterLevel())
        		.characterClass(characterAchievementInfo.getCharacterClass())
        		.trophyGrade(achievementTop10Rankings.get(0).getTrophyGrade())
        		.trophyScore(achievementTop10Rankings.get(0).getTrophyScore())
        		.characterImage(characterAchievementInfo.getCharacterImage())
        		.build();
        // 유니온 랭킹 1위 ocid
        CharacterIdDTO unionTop1OCID = maleCharacterApiService.getCharacterId(unionTop10Rankings.get(0).getCharacterName());
        // 유니온 랭킹 1위 캐릭터 기본 정보
        CharacterBasicDTO characterUnionInfo = maleCharacterApiService.getCharacterBasic(previousDate, unionTop1OCID.getOcid());
        Top1UnionRankingDTO top1UnionRanking = Top1UnionRankingDTO.builder()
        		.characterName(characterUnionInfo.getCharacterName())
        		.worldName(characterUnionInfo.getWorldName())
        		.characterLevel(characterUnionInfo.getCharacterLevel())
        		.characterClass(characterUnionInfo.getCharacterClass())
        		.unionLevel(unionTop10Rankings.get(0).getUnionLevel())
        		.unionPower(unionTop10Rankings.get(0).getUnionPower())
        		.characterImage(characterUnionInfo.getCharacterImage())
        		.build();

        MainDTO mainDTO = MainDTO.builder()
                .baseRankings(baseTop10Rankings)
                .rebootRankings(rebootTop10Rankings)
                .dojangRankings(dojangTop10Rankings)
                .guildRankings(guildTop10Rankings)
                .top1LevelRanking(top1LevelRanking)
                .top1DojangRanking(top1DojangRanking)
                .top1AchievementRanking(top1AchievementRanking)
                .top1UnionRanking(top1UnionRanking)
                .build();
        
        log.info("Top10 랭킹 리스트 및 Top1랭킹 정보 --> " + mainDTO);		
        return new ResponseEntity<>(
                ResDTO.<MainDTO>builder()
                        .code(0)
                        .data(mainDTO)
                        .message("캐릭터 랭킹 정보를 불러왔습니다.")
                        .build(),
                        HttpStatus.OK
        );
    } 
}