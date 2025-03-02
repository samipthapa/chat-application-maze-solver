package com.samip.chatmaze.chat.controller;

import com.samip.chatmaze.chat.entity.ChatMessage;
import com.samip.chatmaze.chat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@Slf4j
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PutMapping("/save/{id}")
    public ChatMessage saveMessage(@PathVariable("id") Long id) {
        return messageService.changeMessageStatus(id);
    }

    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> findMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("receiverId") String receiverId
    ) {
        return ResponseEntity.ok(messageService.getConversation(senderId, receiverId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }

//    Only allowing the content to be updated
    @PutMapping("/updateContent/{id}")
    public ChatMessage changeContent(@PathVariable("id") Long id, @RequestBody ChatMessage message) {
        return messageService.changeMessageContent(id, message.getContent());
    }
}
