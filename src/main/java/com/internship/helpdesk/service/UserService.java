package com.internship.helpdesk.service;

import com.internship.helpdesk.dto.request.CreateUserRequest;
import com.internship.helpdesk.dto.request.UpdateUserRequest;
import com.internship.helpdesk.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

    List<UserResponse> getUsersByDepartment(Long departmentId);
}