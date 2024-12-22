package com.xavier.mozprops_api.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xavier.mozprops_api.storage.ImageStorage;

import lombok.extern.slf4j.Slf4j;

@Service
@Profile("dev")
@Slf4j
public class LocalImageStorageService implements ImageStorage {

    @Value("${file.storage-location}")
    private String storageLocation;

    @Override
    public String upload(MultipartFile file, String fileName) throws IOException {
        try {
            File dir = new File(storageLocation);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File destinationFile = new File(dir, fileName);
            file.transferTo(destinationFile);
            return destinationFile.getAbsolutePath();
        } catch (Exception e) {
            log.error("Error uploading image", e);
            throw new IOException("Error uploading image", e);
        }
    }

   
    
}
