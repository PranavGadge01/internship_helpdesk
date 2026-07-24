package com.internship.helpdesk.auth.dto;

import com.internship.helpdesk.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;

    private String type;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

}
