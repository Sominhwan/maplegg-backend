package kr.co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 비동기 처리 가능
@EnableCaching // 캐싱 처리 가능
@SpringBootApplication
public class MapleBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MapleBackendApplication.class, args);
	}

}
