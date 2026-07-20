package com.internship.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column(nullable = false,unique = true,length = 100)
    private String deptName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active=true;

}
