package kr.co.maple.module.characterInfo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.maple.module.characterInfo.service.CharacterInfoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CharacterInfoController {
	private final CharacterInfoService characterService;
	
	@GetMapping(value="/character/stat") 
	public HttpEntity<?> characterStat(@RequestParam("characterName") String characterName) {
        return characterService.characterStat(characterName);
	}
}
