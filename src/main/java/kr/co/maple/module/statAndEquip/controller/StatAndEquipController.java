package kr.co.maple.module.statAndEquip.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.maple.module.statAndEquip.service.StatAndEquipService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StatAndEquipController {
	private final StatAndEquipService statAndEquipService;
	
	@GetMapping(value="/character/equipment")
	public HttpEntity<?> equipment(@RequestParam("characterName") String characterName) {
        return statAndEquipService.equipment(characterName);
	}
}
