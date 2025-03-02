package com.samip.chatmaze.chat.service;

import com.samip.chatmaze.chat.entity.ChatFile;
import com.samip.chatmaze.chat.entity.ChatMessage;
import com.samip.chatmaze.chat.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return messageRepository.save(chatMessage);
    }

    public ChatMessage findMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<ChatMessage> getConversation(String userA, String userB) {
        return messageRepository.findMessagesBetweenUsers(userA, userB);
    }

    public ChatMessage changeMessageStatus(Long id) {
        ChatMessage message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            message.setSaved(!message.isSaved());
            return messageRepository.save(message);
        }
        return null;
    }

//    Delete unsaved messages older than 5 minutes
    @Scheduled(fixedRate = 300000)
    public void deleteOldUnsavedMessages() {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);

        List<ChatMessage> oldMessages = messageRepository.findBySavedFalseAndTimestampBefore(fiveMinutesAgo);

        if (!oldMessages.isEmpty()) {
            messageRepository.deleteAll(oldMessages);
            log.info("Deleted old unsaved messages: {}", oldMessages);
        }
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public ChatMessage changeMessageContent(Long id, String content) {
        ChatMessage message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            message.setContent(content);
            return messageRepository.save(message);
        }
        return null;
    }
}
