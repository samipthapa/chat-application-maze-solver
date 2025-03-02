package com.samip.chatmaze.chat.service;

import com.samip.chatmaze.chat.entity.ChatUser;
import com.samip.chatmaze.chat.exception.NotFoundException;
import com.samip.chatmaze.chat.repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;

    @Autowired
    public ChatUserService(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public ChatUser saveUser(ChatUser user) {
        return chatUserRepository.save(user);
    }

    public List<ChatUser> getAllUsers() {
        return chatUserRepository.findAll();
    }

//    To be used when fetching all users except the current user
    public List<ChatUser> getAllUsersExcept(String username) {
        return chatUserRepository.findByUsernameNot(username);
    }

    public ChatUser getUser(String username) {
        ChatUser user = chatUserRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found: " + username);
        }
        return user;
    }

    public ChatUser updateUser(String username, ChatUser user) {
        ChatUser existingUser = getUser(username);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        return chatUserRepository.save(existingUser);
    }

    public void deleteUser(String username) {
        ChatUser user = getUser(username);
        chatUserRepository.delete(user);
    }
}
