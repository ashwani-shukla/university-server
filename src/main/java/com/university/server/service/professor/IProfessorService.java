package com.university.server.service.professor;

import com.university.server.dto.ProfessorCoursesDto;
import com.university.server.dto.ProfessorDto;
import com.university.server.exception.ResourceNotFoundException;

import java.util.List;


/**
 * The interface Professor service.
 */
public interface IProfessorService {

    /**
     * Gets professors.
     *
     * @return the professors
     */
    List<ProfessorDto> getProfessors();

    /**
     * Gets professors with courses.
     *
     * @return the professors with courses
     */
    List<ProfessorCoursesDto> getProfessorsWithCourses();

    /**
     * Gets professor by id.
     *
     * @param professorId the professor id
     * @return the professor by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    ProfessorDto getProfessorById(Integer professorId) throws ResourceNotFoundException;

    /**
     * Save professor.
     *
     * @param professorDtoRequest the professor dto request
     * @return the professor
     */
    ProfessorDto saveProfessor(ProfessorDto professorDtoRequest);

    /**
     * Update professor.
     *
     * @param professorId         the professor id
     * @param professorDtoRequest the professor dto request
     * @return the professor
     * @throws ResourceNotFoundException the resource not found exception
     */
    ProfessorDto updateProfessor(Integer professorId, ProfessorDto professorDtoRequest) throws ResourceNotFoundException;

    /**
     * Delete professor.
     *
     * @param professorId the professor id
     */
    void deleteProfessor(Integer professorId);
}
