package com.samip.chatmaze.chat.repository;

import com.samip.chatmaze.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("FROM ChatMessage where (sender=:theSender AND receiver=:theReceiver) " +
            "OR (sender=:theReceiver AND receiver=:theSender) ORDER BY timestamp")
    List<ChatMessage> findMessagesBetweenUsers(@Param("theSender") String theSender,
                                               @Param("theReceiver") String theReceiver);

    List<ChatMessage> findBySavedFalseAndTimestampBefore(LocalDateTime timestamp);
}
