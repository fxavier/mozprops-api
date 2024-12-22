package com.xavier.mozprops_api.storage.s3;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.xavier.mozprops_api.storage.ImageStorage;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Profile("prod")
@Slf4j
@RequiredArgsConstructor
public class S3ImageStorageService implements ImageStorage {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private final String bucketName;

    @Override
    public String upload(MultipartFile file, String fileName) throws IOException {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName,
                fileName, 
                file.getInputStream(),
                null
            ).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            log.error("Error uploading image", e);
            throw new RuntimeException("Error uploading image to S3", e);
        }
    }

  
}
