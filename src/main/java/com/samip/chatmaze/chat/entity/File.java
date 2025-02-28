package com.samip.chatmaze.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "mime_type")
    private String mimeType;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
