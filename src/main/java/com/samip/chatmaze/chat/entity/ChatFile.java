package com.samip.chatmaze.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "file_path")
    private String filePath;

    @Column(name ="size")
    private Long size;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
