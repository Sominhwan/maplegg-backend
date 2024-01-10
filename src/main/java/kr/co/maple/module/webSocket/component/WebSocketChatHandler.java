package kr.co.maple.module.webSocket.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.maple.module.webSocket.model.ChatDTO;
import kr.co.maple.module.webSocket.model.ChatRoom;
import kr.co.maple.module.webSocket.model.ChatDTO.MessageType;
import kr.co.maple.module.webSocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final ChatService service;
    private final Map<String, WebSocketSession> sessions = new HashMap<>();
    
    //최초 연결 시
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final String sessionId = session.getId();
		final String enteredMessage = sessionId + "님이 입장하셨습니다.";
        sessions.put(sessionId, session);

        sessions.values().forEach((s) -> {
            try {
                if(!s.getId().equals(sessionId) && s.isOpen()) {  
                    s.sendMessage(new TextMessage(enteredMessage));
                }
            } catch (IOException e) {}
        });
    }
    //양방향 데이터 통신할 떄 해당 메서드가 call 된다.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	System.out.println("로그 --> " + message);

        ChatDTO chatMessage = mapper.readValue(message.getPayload(), ChatDTO.class);
        final String sessionId = session.getId();
        sendMessage(sessionId, message, chatMessage);
    }
    
    //웹소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final String sessionId = session.getId();
        final String leaveMessage = sessionId + "님이 떠났습니다.";
        sessions.remove(sessionId); // 삭제
        ChatDTO chatDTO = ChatDTO.builder()
        					.type(MessageType.EXIT)
        					.message("종료")
        					.build();
        sendMessage(sessionId, new TextMessage(leaveMessage), chatDTO);

    }

    //통신 에러 발생 시
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {}

    private <T> void sendMessage(String sessionId, WebSocketMessage<?> message, ChatDTO chatDTO) {
    	
        sessions.values().forEach(s -> {
            if(!s.getId().equals(sessionId) && s.isOpen()) {
                try {
                    s.sendMessage(new TextMessage(mapper.writeValueAsString(chatDTO)));
                } catch (IOException e) {}
            }
        });
    }
}