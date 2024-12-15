package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.UserRequest;
import com.xavier.mozprops_api.dto.UserResponse;

import jakarta.validation.Valid;

public interface UserService {
    UserResponse create(@Valid UserRequest userRequest);

    UserResponse update(Long id, @Valid UserRequest userRequest);

    List<UserResponse> findAll();

    UserResponse findOne(Long id);

    UserResponse findByEmail(String email);

    void delete(Long id);

}
