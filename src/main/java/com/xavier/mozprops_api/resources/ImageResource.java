package com.xavier.mozprops_api.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xavier.mozprops_api.models.PropertyImages;
import com.xavier.mozprops_api.service.PropertyService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/{propertyId}/images")
@RequiredArgsConstructor
public class ImageResource {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyImages> uploadImage(
        @PathVariable Long propertyId, @RequestParam("image") MultipartFile image) throws IOException {
        PropertyImages uploadedImage = propertyService.uploadImage(propertyId, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedImage);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long propertyId, @PathVariable Long imageId) {
        propertyService.deleteImage(propertyId, imageId);
        return ResponseEntity.noContent().build();
    }
    
}
