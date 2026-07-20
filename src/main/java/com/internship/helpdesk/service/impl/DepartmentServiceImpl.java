package com.internship.helpdesk.service.impl;

import com.internship.helpdesk.entity.Department;
import com.internship.helpdesk.repository.DepartmentRepository;
import com.internship.helpdesk.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department){

        if (departmentRepository.existsByDeptName(department.getDeptName())) {
            throw new RuntimeException("Department already exists");
        }

        // returns the new object with the id and other feilds
        return departmentRepository.save(department);

    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentByDeptId(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (!existingDepartment.getDeptName().equals(department.getDeptName())
                && departmentRepository.existsByDeptName(department.getDeptName())) {

            throw new RuntimeException("Department already exists");
        }

        existingDepartment.setDeptName(department.getDeptName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setActive(department.getActive());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long deptId) {

        departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.deleteById(deptId);
    }

}
