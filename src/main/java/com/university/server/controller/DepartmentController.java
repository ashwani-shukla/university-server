package com.university.server.controller;

import com.university.server.dto.DepartmentDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.service.department.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * The type Department controller.
 */
@RestController
@RequestMapping("university/api/v1/")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * Instantiates a new Department controller.
     *
     * @param departmentService the department service
     */
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Gets departments.
     *
     * @return the departments
     */
    @GetMapping("/department")
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    /**
     * Gets department by id.
     *
     * @param departmentId the department id
     * @return the department by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/department/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Integer departmentId) throws ResourceNotFoundException {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    /**
     * Create department response entity.
     *
     * @param departmentDto the department dto
     * @return the response entity
     */
    @PostMapping("/department")
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {

        DepartmentDto departmentResponse = departmentService.saveDepartment(departmentDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(departmentResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Update department response entity.
     *
     * @param departmentId  the department id
     * @param departmentDto the department dto
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/department/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Integer departmentId,
                                                       @Valid @RequestBody DepartmentDto departmentDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, departmentDto));
    }

    /**
     * Delete department response entity.
     *
     * @param departmentId the department id
     * @return the response entity
     */
    @DeleteMapping("/department/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok().build();
    }

}
