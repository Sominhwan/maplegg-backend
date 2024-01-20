package kr.co.maple.common.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("characterRankingCache", "characterInfo");
        cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(240, TimeUnit.MINUTES));
        return cacheManager;
    } 
    // 매일 오전 8시 30분에 실행되면서 캐시를 갱신
    @CacheEvict(value = "characterRankingCache", allEntries = true)
    @Scheduled(cron = "0 30 8 * * *")
    public void evictAndRefreshCache() {}
    // 4시간마다 캐시 갱신
    @Cacheable(value = "characterRankingCache")
    @Scheduled(fixedRate = 14400000) // 4시간마다 (밀리초)
    public void cacheCharacterRanking() {}
    
    // 매일 오전 8시 30분에 실행되면서 캐시를 갱신
    @CacheEvict(value = "characterInfo", allEntries = true)
    @Scheduled(cron = "0 30 8 * * *")
    public void evictAndRefreshCache2() {}
    // 4시간마다 캐시 갱신
    @Cacheable(value = "characterInfo")
    @Scheduled(fixedRate = 14400000) // 4시간마다 (밀리초)
    public void cacheCharacterInfo() {}
}
