package com.internship.helpdesk.service;

import com.internship.helpdesk.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentByDeptId(Long deptId);

    Department updateDepartment(Long id,Department department);

    void deleteDepartment(Long id);

}
