package com.samip.chatmaze;

import com.samip.chatmaze.chat.entity.ChatUser;
import com.samip.chatmaze.chat.repository.ChatUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private ChatUserRepository userRepository;

    @Test
    public void testDatabaseConnection() {

        ChatUser user = ChatUser.builder()
                .username("test")
                .firstName("Test")
                .lastName("User")
                .build();

        userRepository.save(user);

        ChatUser savedUser = userRepository.findById("test").orElse(null);
        assertTrue(savedUser != null && "test".equals(savedUser.getUsername()),
                "Database connection and data retrieval failed");
    }
}
