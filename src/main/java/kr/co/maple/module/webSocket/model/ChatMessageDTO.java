package kr.co.maple.module.webSocket.model;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private String messageType; // 메시지 타입
    private Long chatRoomId; // 방 번호
    private Long sendeId; // 채팅을 보낸 사람
    private String message; // 메시지
    private String subtitle;
    private String content;
}