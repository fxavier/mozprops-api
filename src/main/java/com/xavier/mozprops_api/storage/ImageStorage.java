package com.xavier.mozprops_api.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {
    String upload(MultipartFile file) throws IOException;
}
