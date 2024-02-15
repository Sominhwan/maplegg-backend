package kr.co.maple.module.union.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.maple.module.union.service.UnionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1")
public class UnionController {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final UnionService unionService;
	
	@GetMapping(value="/character/union")
	public HttpEntity<?> characterUnion(@RequestParam("characterName") String characterName) {
		return unionService.characterUnion(characterName);
	}
}
