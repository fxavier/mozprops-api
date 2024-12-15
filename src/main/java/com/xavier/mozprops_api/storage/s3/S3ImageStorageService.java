package com.xavier.mozprops_api.storage.s3;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.xavier.mozprops_api.storage.ImageStorage;


import lombok.RequiredArgsConstructor;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class S3ImageStorageService implements ImageStorage {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private final String bucketName;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        return amazonS3.getUrl(bucketName, fileName).toString();
    }
    
}
