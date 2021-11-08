package com.university.server.repository;

import com.university.server.dto.ProfessorCourseDto;
import com.university.server.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Professor repository.
 */
@Repository
public interface IProfessorRepository extends JpaRepository<Professor, Integer> {
    @Query(name = "find_professor_course_dto", nativeQuery = true)
    List<ProfessorCourseDto> findAllProfessorAndCourses();
}