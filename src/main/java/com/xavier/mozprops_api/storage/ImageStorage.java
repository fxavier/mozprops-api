package com.xavier.mozprops_api.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {

    /**
     * Uploads an image to the storage and returns the URL of the image.
     * @param file The image file to upload.
     * @param fileName The name of the file to upload.
     * @return The URL of the uploaded image.
     * @throws IOException If an error occurs while uploading the image.
     */
    String upload(MultipartFile file, String fileName) throws IOException;
}
