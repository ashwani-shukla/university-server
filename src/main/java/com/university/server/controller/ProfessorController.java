package com.university.server.controller;

import com.university.server.dto.ProfessorCoursesDto;
import com.university.server.dto.ProfessorDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.service.professor.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * The type Professor controller.
 */
@RestController
@RequestMapping("university/api/v1/")
@Validated
public class ProfessorController {

    private final ProfessorService professorService;

    /**
     * Instantiates a new Professor controller.
     *
     * @param professorService the professor service
     */
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * Gets professors.
     *
     * @return the professors
     */
    @GetMapping("/professor")
    public ResponseEntity<List<ProfessorDto>> getProfessors() {
        return ResponseEntity.ok(professorService.getProfessors());
    }

    /**
     * Gets professor and courses.
     *
     * @return the professor and courses
     */
    @GetMapping("/professor/search")
    public ResponseEntity<List<ProfessorCoursesDto>> getProfessorAndCourses() {
        return ResponseEntity.ok(professorService.getProfessorsWithCourses());
    }

    /**
     * Gets professor by id.
     *
     * @param professorId the professor id
     * @return the professor by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/professor/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable("id") Integer professorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(professorService.getProfessorById(professorId));
    }

    /**
     * Create professor response entity.
     *
     * @param professorDto the professor dto
     * @return the response entity
     */
    @PostMapping("/professor")
    public ResponseEntity<?> createProfessor(@Valid @RequestBody ProfessorDto professorDto) {

        ProfessorDto professorResponse = professorService.saveProfessor(professorDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(professorResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Update professor response entity.
     *
     * @param professorId  the professor id
     * @param professorDto the professor dto
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/professor/{professorId}")
    public ResponseEntity<?> updateProfessor(@PathVariable Integer professorId,
                                              @Valid @RequestBody ProfessorDto professorDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(professorService.updateProfessor(professorId, professorDto));
    }

    /**
     * Delete professor response entity.
     *
     * @param professorId the professor id
     * @return the response entity
     */
    @DeleteMapping("/professor/{professorId}")
    public ResponseEntity<?> deleteProfessor(@PathVariable Integer professorId) {
        professorService.deleteProfessor(professorId);
        return ResponseEntity.ok().build();
    }

}
