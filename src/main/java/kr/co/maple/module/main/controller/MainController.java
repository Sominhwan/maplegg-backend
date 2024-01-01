package kr.co.maple.module.main.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.maple.module.main.service.MainService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/main")
public class MainController {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final MainService mainService;
	
	@GetMapping(value="/character/ranking/overall")
	public HttpEntity<?> characterRankingOverall() {
		return mainService.characterRankingOverall();
	}
}
