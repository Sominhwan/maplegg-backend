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
	private static final Logger log = LogManager.getLogger("kr.co.WebbClient");
	/**
	 * 
	 * @ method : webClientBuilder
	 * @ 기능 : WebClient 버퍼 크기 증가
	 * @ params: WebClient
	 */
    private WebClient.Builder webClientBuilder() {
        return WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(5 * 1024 * 1024)); // 5MB
    }
	/**
	 * 
	 * @ method : webClientGetApi
	 * @ 기능 : http get
	 * @ params: *
	 */
    public <T> T webClientGetApi(String apiUrl, MultiValueMap<String, String> params, String key, String value, Class<T> responseType) {
        WebClient webClient = webClientBuilder().baseUrl(apiUrl).build();

        return webClient.method(HttpMethod.GET)
        		.uri(uriBuilder -> 
        				uriBuilder.queryParams(params).build()
				)
        		.header(key, value)
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
                .doOnError(error -> {
                	log.info("eror --> " + error);
                })
                .doOnSuccess(responseBody -> {
                	log.info("responseBody --> " + responseBody);
                })
                .block();
    }
	/**
	 * 
	 * @ method : webClientPostApi
	 * @ 기능 : http post
	 * @ params: *
	 */
    public <T> T webClientPostApi(String apiUrl, Object requestObject, String key, String value, Class<T> responseType) {
        WebClient webClient = WebClient.create(apiUrl);

        return webClient.method(HttpMethod.POST)
                .uri(uriBuilder -> uriBuilder.build())
                .header(key, value)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestObject)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.info(response.statusCode() + " 에러");
                    throw new RuntimeException("Client Error");
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    log.info(response.statusCode() + " 에러");
                    throw new RuntimeException("Internal Server Error");
                })
                .bodyToMono(responseType)
                .block();
    }
}
