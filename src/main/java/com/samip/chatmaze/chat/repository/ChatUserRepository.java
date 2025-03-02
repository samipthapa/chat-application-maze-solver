package com.samip.chatmaze.chat.repository;

import com.samip.chatmaze.chat.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, String> {

  ChatUser findByUsername(String username);

    List<ChatUser> findByUsernameNot(String username);
}
