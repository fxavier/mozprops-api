package com.xavier.mozprops_api.storage.local;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xavier.mozprops_api.storage.ImageStorage;

@Service
public class LocalImageStorageService implements ImageStorage{

    @Value("${local.storage.location}")
    private String storageLocation;

    @Override
    public String upload(MultipartFile file) throws IOException {
        Path dirPath = Paths.get(storageLocation).toAbsolutePath().normalize();
        Files.createDirectories(dirPath);

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = dirPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "/images/" + fileName;
    }
    
}
