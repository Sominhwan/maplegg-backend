package kr.co.maple.module.webSocket.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.maple.module.webSocket.model.ChatRoom;
import kr.co.maple.module.webSocket.model.ChatVO;
import kr.co.maple.module.webSocket.service.ChatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatController {
	private static final Logger log = LogManager.getLogger("kr.co.chat");
    private final ChatService service;

    @PostMapping(value="/chat")
    public ChatRoom createRoom(@RequestBody ChatVO chatVO){
        return service.createRoom(chatVO.getName());
    }
}
