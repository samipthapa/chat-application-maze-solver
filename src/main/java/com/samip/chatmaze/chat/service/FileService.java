package com.samip.chatmaze.chat.service;

import com.samip.chatmaze.chat.entity.ChatFile;
import com.samip.chatmaze.chat.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FileService {

    private final String STORAGE_DIRECTORY = "D:\\Storage";
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public ChatFile storeFile(MultipartFile file) throws IOException {

        if (file == null) {
            throw new RuntimeException("File cannot be empty");
        }
        if (file.getContentType() != null && !isValidMimeType(file.getContentType())) {
            throw new RuntimeException("Invalid file type");
        }
        File directory = new File(STORAGE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new RuntimeException("Invalid file path");
        }
        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        ChatFile chatFile = ChatFile.builder()
                .fileName(file.getOriginalFilename())
                .filePath(targetFile.getAbsolutePath())
                .mimeType(file.getContentType())
                .size(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .build();

        return fileRepository.save(chatFile);
    }

    public ChatFile getFile(Long fileId) throws IOException {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    private boolean isValidMimeType(String contentType) {
        return contentType.equals("image/jpeg")
                || contentType.equals("image/jpg")
                || contentType.equals("image/png")
                || contentType.equals("application/pdf");
    }
}
