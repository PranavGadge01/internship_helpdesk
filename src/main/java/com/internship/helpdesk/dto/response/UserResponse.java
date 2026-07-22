package com.internship.helpdesk.dto.response;

import com.internship.helpdesk.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private String departmentName;

    private Boolean active;
}