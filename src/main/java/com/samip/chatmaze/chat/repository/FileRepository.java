package com.samip.chatmaze.chat.repository;

import com.samip.chatmaze.chat.entity.ChatFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<ChatFile, Long> {

}
