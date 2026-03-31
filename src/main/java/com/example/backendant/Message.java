package com.example.backendant;

import java.time.LocalDateTime;

public record Message(
        Long id,
        Long senderId,
        Long recipientId,
        String textMessage,
        LocalDateTime sendingTime,
        MessageStatus messageStatus
){
}
