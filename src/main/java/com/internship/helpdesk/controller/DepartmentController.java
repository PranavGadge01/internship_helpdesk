package com.internship.helpdesk.controller;

import com.internship.helpdesk.entity.Department;
import com.internship.helpdesk.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create Department
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {

        Department savedDepartment = departmentService.createDepartment(department);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    // Get All Departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {

        List<Department> departments = departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {

        Department department = departmentService.getDepartmentByDeptId(id);

        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        Department updatedDepartment =
                departmentService.updateDepartment(id, department);

        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully");
    }

}