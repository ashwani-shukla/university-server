package com.university.server.service.department;

import com.university.server.dto.DepartmentDto;
import com.university.server.exception.ResourceNotFoundException;

import java.util.List;


/**
 * The interface Department service.
 */
public interface IDepartmentService {

    /**
     * Gets departments.
     *
     * @return the departments
     */
    List<DepartmentDto> getDepartments();

    /**
     * Gets department by id.
     *
     * @param departmentId the department id
     * @return the department by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    DepartmentDto getDepartmentById(Integer departmentId) throws ResourceNotFoundException;

    /**
     * Save department.
     *
     * @param departmentDtoRequest the department dto request
     * @return the department
     */
    DepartmentDto saveDepartment(DepartmentDto departmentDtoRequest);

    /**
     * Update department.
     *
     * @param departmentId         the department id
     * @param departmentDtoRequest the department dto request
     * @return the department
     * @throws ResourceNotFoundException the resource not found exception
     */
    DepartmentDto updateDepartment(Integer departmentId, DepartmentDto departmentDtoRequest) throws ResourceNotFoundException;

    /**
     * Delete department.
     *
     * @param departmentId the department id
     */
    void deleteDepartment(Integer departmentId);
}
