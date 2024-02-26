package kr.co.maple.module.skillAndSymbol.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.maple.module.skillAndSymbol.service.SkillAndSymbolService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SkillAndSymbolController {
	private final SkillAndSymbolService skillAndSymbolService;
	
	@GetMapping(value="/character/skill")
	public HttpEntity<?> skill(@RequestParam("characterName") String characterName) {
        return skillAndSymbolService.skillAndSymbol(characterName);
	}
}
