package com.samip.chatmaze;

import com.samip.chatmaze.chat.entity.ChatMessage;
import com.samip.chatmaze.chat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StoreChatMessageTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void storeMessageTest() {

        String sender = "User A";
        String receiver = "User B";
        String content = "Hello, User B!";
        LocalDateTime timestamp = LocalDateTime.now();

        ChatMessage message = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .timestamp(timestamp)
                .build();

        ChatMessage savedMessage = messageService.saveMessage(message);

        assertNotNull(savedMessage.getId(), "The message ID should not be null after saving");

        ChatMessage retrievedMessage = messageService.findMessageById(savedMessage.getId());

        assertNotNull(retrievedMessage, "The message should exist in the database.");
        assertEquals(sender, retrievedMessage.getSender(), "The sender should match.");
        assertEquals(receiver, retrievedMessage.getReceiver(), "The receiver should match.");
        assertEquals(content, retrievedMessage.getContent(), "The content should match.");
    }

}
