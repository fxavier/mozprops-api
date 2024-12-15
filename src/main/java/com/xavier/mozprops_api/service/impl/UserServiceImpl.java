package com.xavier.mozprops_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xavier.mozprops_api.dto.UserRequest;
import com.xavier.mozprops_api.dto.UserResponse;
import com.xavier.mozprops_api.models.User;
import com.xavier.mozprops_api.repository.UserRepository;
import com.xavier.mozprops_api.service.UserService;
import com.xavier.mozprops_api.service.exception.EmailAlreadyTakenException;
import com.xavier.mozprops_api.service.exception.UserNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserResponse create(@Valid UserRequest userRequest) {
        if (isEmailAlreadyTaken(userRequest.getEmail())) {
            throw new EmailAlreadyTakenException("Email already taken");
        }
        User user = toEntity(userRequest);
        userRepository.save(user);
        return toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, @Valid UserRequest userRequest) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setRole(userRequest.getRole());
        userRepository.save(user);
        return toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public UserResponse findOne(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return toResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return toResponse(user.get());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .role(user.getRole())
            .isVerified(user.getIsVerified())
            .verificationToken(user.getVerificationToken())
            .verificationExpires(user.getVerificationExpires())
            .resetPasswordToken(user.getResetPasswordToken())
            .resetPasswordExpires(user.getResetPasswordExpires())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }

    private User toEntity(UserRequest userRequest) {
        return User.builder()
            .name(userRequest.getName())
            .email(userRequest.getEmail())
            .phone(userRequest.getPhone())
            .role(userRequest.getRole())
            .build();
    }

    private boolean isEmailAlreadyTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
