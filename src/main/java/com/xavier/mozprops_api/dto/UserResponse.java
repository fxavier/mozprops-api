package com.xavier.mozprops_api.dto;

import java.time.LocalDateTime;

import com.xavier.mozprops_api.models.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private Boolean isVerified;
    private String verificationToken;
    private LocalDateTime verificationExpires;
    private String resetPasswordToken;
    private LocalDateTime resetPasswordExpires;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
