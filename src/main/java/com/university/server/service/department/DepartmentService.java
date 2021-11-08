package com.university.server.service.department;

import com.university.server.dto.DepartmentDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.model.Department;
import com.university.server.repository.IDepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Department service.
 */
@Service
public class DepartmentService implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Department service.
     *
     * @param departmentRepository the department repository
     * @param modelMapper          the model mapper
     */
    public DepartmentService(IDepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll().stream().map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Integer departmentId) throws ResourceNotFoundException {
        Optional<Department> departmentList = departmentRepository.findById(departmentId);
        if(departmentList.isEmpty())
            throw new ResourceNotFoundException("Department not found with id: " + departmentId);
        return modelMapper.map(departmentList.get(), DepartmentDto.class);
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department departmentRequest = modelMapper.map(departmentDto, Department.class);
        return modelMapper.map(departmentRepository.save(departmentRequest), DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(Integer departmentId, DepartmentDto departmentDto) throws ResourceNotFoundException {
        return departmentRepository.findById(departmentId)
                .map(department -> {
                    Department departmentRequest = modelMapper.map(departmentDto, Department.class);
                    department.setName(departmentRequest.getName());
                    return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        department.ifPresent(departmentRepository::delete);
    }

}
