package com.university.server.repository;

import com.university.server.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Course repository.
 */
@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
}