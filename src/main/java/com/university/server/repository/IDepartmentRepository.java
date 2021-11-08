package com.university.server.repository;

import com.university.server.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Department repository.
 */
@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}