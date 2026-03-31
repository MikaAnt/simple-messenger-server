package com.example.backendant;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(Message messageToSend){
        var entityToSave = new MessageEntity(
                null,
                messageToSend.senderId(),
                messageToSend.recipientId(),
                messageToSend.textMessage(),
                LocalDateTime.now(),
                MessageStatus.NOT_SAVED
        );
        var savedEntity = messageRepository.save(entityToSave);
        return toMessage(savedEntity);
    }

    public List<Message> getUnsavedMessages(Long senderId, Long recipientId){
        var messagesFromSender = messageRepository.findAllBySenderId(senderId);
        var messagesToRecipient = messageRepository.findAllByRecipientId(recipientId);
        messagesFromSender.retainAll(messagesToRecipient);
        messagesFromSender.removeIf(message ->
                message.getMessageStatus() == MessageStatus.SAVED);

        return messagesFromSender.stream()
                .map(this::toMessage)
                .toList();
    }

    public Message saveMessageById(Long id){
        MessageEntity messageEntity = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found Message by id = " + id));
        var messageToSave = new MessageEntity(
                messageEntity.getId(),
                messageEntity.getSenderId(),
                messageEntity.getRecipientId(),
                messageEntity.getTextMessage(),
                messageEntity.getSendingTime(),
                MessageStatus.SAVED
        );
        var savedMessage = messageRepository.save(messageToSave);
        return toMessage(savedMessage);
    }

    public void deleteMessageById(Long id){
        if(!messageRepository.existsById(id)){
            throw new EntityNotFoundException("Not found Message by id=" + id);
        }
        messageRepository.deleteById(id);
    }

    private Message toMessage(MessageEntity messageEntity){
        return new Message(
                messageEntity.getId(),
                messageEntity.getSenderId(),
                messageEntity.getRecipientId(),
                messageEntity.getTextMessage(),
                messageEntity.getSendingTime(),
                messageEntity.getMessageStatus()
        );
    }

}
