package com.internship.helpdesk.service.impl;

import com.internship.helpdesk.dto.request.CreateUserRequest;
import com.internship.helpdesk.dto.request.UpdateUserRequest;
import com.internship.helpdesk.dto.response.UserResponse;
import com.internship.helpdesk.entity.Department;
import com.internship.helpdesk.entity.User;
import com.internship.helpdesk.exception.DepartmentNotFoundException;
import com.internship.helpdesk.exception.EmailAlreadyExistsException;
import com.internship.helpdesk.exception.UserNotFoundException;
import com.internship.helpdesk.repository.DepartmentRepository;
import com.internship.helpdesk.repository.UserRepository;
import com.internship.helpdesk.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public UserServiceImpl(UserRepository userRepository,
                           DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found"));

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setDepartment(department);
        user.setActive(request.getActive());

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        if (!existingUser.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found"));

        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(request.getPassword());
        existingUser.setRole(request.getRole());
        existingUser.setDepartment(department);
        existingUser.setActive(request.getActive());

        User updatedUser = userRepository.save(existingUser);

        return mapToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public List<UserResponse> getUsersByDepartment(Long departmentId) {

        return userRepository.findByDepartmentDeptId(departmentId)
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToUserResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setDepartmentName(user.getDepartment().getDeptName());
        response.setActive(user.getActive());

        return response;
    }
}