package com.internship.helpdesk.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponse {

    private Long deptId;
    private String deptName;
}