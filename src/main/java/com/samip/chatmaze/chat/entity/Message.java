package com.samip.chatmaze.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
