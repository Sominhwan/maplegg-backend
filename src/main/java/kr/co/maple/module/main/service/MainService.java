package kr.co.maple.module.main.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import kr.co.maple.common.model.ResDTO;
import kr.co.maple.module.main.model.RankingListDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	@Value("${maple.apiKey}")
	private String API_KEY;
	@Value("${maple.url}")
	private String BASE_URL;

	public HttpEntity<?> characterRankingOverall() {
	    // String characterName = URLEncoder.encode("법사로키우기", StandardCharsets.UTF_8);
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("date", "2023-12-22");
	    params.add("world_type", "0");
	    
	    RankingListDTO rankingDTOList = WebClient.create(BASE_URL + "/maplestory/v1/ranking/overall")
								.get()
								.uri(uriBuilder ->
										uriBuilder.queryParams(params).build()
								)
								.header("x-nxopen-api-key", API_KEY)
								.accept(MediaType.APPLICATION_JSON)
				                .retrieve()
					            .onStatus(HttpStatus::is4xxClientError, response -> {
					            	log.info(response.statusCode()  + " 에러");
					            	throw new RuntimeException("Client Error");
					            })
								.onStatus(HttpStatus::is5xxServerError, response -> {
									log.info(response.statusCode()  + " 에러");
									throw new RuntimeException("Internal Server Error");
								})
				                .bodyToMono(RankingListDTO.class)			          
				           		.block(); 
		System.out.println(rankingDTOList);			
					
		return new ResponseEntity<>(
				ResDTO.builder()
						.code(0)
						.data(rankingDTOList)
						.message("캐릭터 랭키 정보를 불러왔습니다.")
						.build(),
						HttpStatus.OK);
				
	}
}
