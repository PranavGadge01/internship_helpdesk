package com.internship.helpdesk.repository;

import com.internship.helpdesk.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    // in the repository the names after keywords should be Starting with the capital letters
    // for eg original is the deptId but make it DeptId
    Optional<Department> findByDeptName(String deptName);

    boolean existsByDeptName(String deptName);

}
