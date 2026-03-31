package com.example.backendant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/message")
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody Message messageToSend
    ) {
        log.info("Called sendMessage");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messageService.sendMessage(messageToSend));
    }

    @GetMapping("/get/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> getUnsavedMessages(
            @PathVariable("senderId") Long senderId,
            @PathVariable("recipientId") Long recipientId
    ){
        log.info("Called getUnreadMessages senderId={}; recipientId={}",
                senderId, recipientId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.getUnsavedMessages(senderId, recipientId));
    }

    @PutMapping("/save/{id}")
    public ResponseEntity<Message> saveMessageById(
            @PathVariable("id") Long id
    ){
        log.info("Called saveMessageById id={}",
                id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.saveMessageById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMessageById(
            @PathVariable("id") Long id
    ){
        log.info("Called deleteMessageById id={}",
                id);
        try {
            messageService.deleteMessageById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}
