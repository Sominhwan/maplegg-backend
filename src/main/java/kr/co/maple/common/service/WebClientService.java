package kr.co.maple.common.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	/**
	 * 
	 * @ 메서드 : webClientGetApi
	 * @ 기능 : http get
	 * @ params: *
	 */
    public <T> T webClientGetApi(String apiUrl, MultiValueMap<String, String> params, String apiKey, Class<T> responseType) {
        WebClient webClient = WebClient.create(apiUrl);

        return webClient.method(HttpMethod.GET)
        		.uri(uriBuilder -> 
        				uriBuilder.queryParams(params).build()
				)
        		.header("x-nxopen-api-key", apiKey)
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
                .bodyToMono(responseType)
                .block();
    }
}
