package com.generic.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generic.jpa.pojo.Department;

public interface DepatmentRepository extends JpaRepository<Department, Long> {

    Department save(Department department);
    List<Department> findAll();

}