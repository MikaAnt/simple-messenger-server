package com.example.backendant;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "messages")
@Entity
public class MessageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "recipient_id")
    private Long recipientId;
    @Column(name = "text_message")
    private String textMessage;
    @Column(name = "sending_time")
    private LocalDateTime sendingTime;
    @Column(name = "message_status")
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    public MessageEntity() {
    }

    public MessageEntity(Long id, Long senderId, Long recipientId, String textMessage,
                         LocalDateTime sendingTime, MessageStatus messageStatus) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.textMessage = textMessage;
        this.sendingTime = sendingTime;
        this.messageStatus = messageStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }


}
