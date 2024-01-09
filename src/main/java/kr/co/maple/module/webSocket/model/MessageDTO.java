package kr.co.maple.module.webSocket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageDTO {
    private String message;
    private String writer;
}
