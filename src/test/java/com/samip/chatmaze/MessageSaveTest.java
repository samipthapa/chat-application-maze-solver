package com.samip.chatmaze;

import com.samip.chatmaze.chat.entity.ChatMessage;
import com.samip.chatmaze.chat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MessageSaveTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void testMarkMessageAsSaved() {

        ChatMessage message = ChatMessage.builder()
                .sender("User A")
                .receiver("User B")
                .content("Hello, User B!")
                .timestamp(LocalDateTime.now())
                .saved(false)
                .build();

        ChatMessage savedMessage = messageService.saveMessage(message);

        savedMessage.setSaved(true);
        messageService.saveMessage(savedMessage);

        ChatMessage updatedMessage = messageService.findMessageById(savedMessage.getId());

        assert updatedMessage != null;
        assertTrue(updatedMessage.isSaved(), "The message should be marked as saved.");
    }

    @Test
    public void testMarkMessageAsUnsaved() {

        ChatMessage message = ChatMessage.builder()
                .sender("User A")
                .receiver("User B")
                .content("Hello, User B!")
                .timestamp(LocalDateTime.now())
                .saved(true)
                .build();

        ChatMessage savedMessage = messageService.saveMessage(message);

        savedMessage.setSaved(false);
        messageService.saveMessage(savedMessage);

        ChatMessage updatedMessage = messageService.findMessageById(savedMessage.getId());

        assert updatedMessage != null;
        assertFalse(updatedMessage.isSaved(), "The message should be marked as unsaved.");
    }
}
