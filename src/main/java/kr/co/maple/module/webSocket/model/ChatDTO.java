package kr.co.maple.module.webSocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChatDTO {
    public ChatDTO() {
        // 디폴트 생성자 추가
    }
    // 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK, EXIT
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private String sender; // 채팅을 보낸 사람
    private String message; // 메시지
    private String time; // 채팅 발송 시간간
}
