package kr.co.maple.module.webSocket.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final ChatService service;
    private static final Logger log = LogManager.getLogger("kr.co.chat");
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>(); // ConcurrentHashMap으로 변경

    // 최초 연결 시
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final String sessionId = session.getId();
        final String enteredMessage = sessionId + "님이 입장하셨습니다.";

        // 동기화 처리 추가
        synchronized (sessions) {
            sessions.put(sessionId, session);

            sessions.values().forEach((s) -> {
                try {
                    if (!s.getId().equals(sessionId) && s.isOpen()) {
                        s.sendMessage(new TextMessage(enteredMessage));
                    }
                } catch (IOException e) {
                    log.error("Error sending message", e);
                }
            });
        }
    }
    // 양방향 데이터 통신할 때 해당 메서드가 호출된다.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            ChatDTO chatMessage = mapper.readValue(message.getPayload(), ChatDTO.class);
            final String sessionId = session.getId();
            log.info(sessionId + " 소켓 메시지 --> " + chatMessage.getMessage());
            
            sendMessage(sessionId, message, chatMessage);
        } catch (Exception e) {
            log.error("Error handling text message", e);
        }
    }
    // 웹소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            final String sessionId = session.getId();
            final String leaveMessage = sessionId + "님이 떠났습니다.";

            // 동기화 처리 추가
            synchronized (sessions) {
                sessions.remove(sessionId); // 삭제
                ChatDTO chatDTO = ChatDTO.builder()
                        .type(MessageType.EXIT)
                        .message("종료")
                        .build();
                sendMessage(sessionId, new TextMessage(leaveMessage), chatDTO);
            }
        } catch (Exception e) {
            log.error("Error handling connection closed", e);
        }
    }
    // 통신 에러 발생 시
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket transport error", exception);
    }

    private <T> void sendMessage(String sessionId, WebSocketMessage<?> message, ChatDTO chatDTO) {
        // 동기화 처리 추가
        synchronized (sessions) {
            sessions.values().forEach(s -> {
                if (!s.getId().equals(sessionId) && s.isOpen()) {
                    try {
                        s.sendMessage(new TextMessage(mapper.writeValueAsString(chatDTO)));
                    } catch (IOException e) {
                        log.error("Error sending message", e);
                    }
                }
            });
        }
    }
}