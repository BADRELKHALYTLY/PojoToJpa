package com.generic.jpa.repository;

import com.generic.jpa.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepatmentRepository extends JpaRepository<Department, Long> {

    Department save(Department department);
    List<Department> findAll();

}