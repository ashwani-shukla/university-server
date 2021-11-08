package com.university.server.service.department;

import com.university.server.dto.DepartmentDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.model.Department;
import com.university.server.repository.IDepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class DepartmentServiceTests {
    private IDepartmentRepository departmentRepository = Mockito.mock(IDepartmentRepository.class);
    private ModelMapper modelMapper = Mockito.mock(ModelMapper.class);

    @Test
    @DisplayName("Should get department by Id")
    public void should_getDepartmentById() throws ResourceNotFoundException {

        DepartmentService departmentService = new DepartmentService(departmentRepository, modelMapper);

        Department department = new Department(1, "New Department", null, null);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setName("New Department");

        Mockito.when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        Mockito.when(modelMapper.map(Optional.of(department), DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto actualDepartmentDtoResponse = departmentService.getDepartmentById(1);

        Assertions.assertThat(actualDepartmentDtoResponse.getId()).isEqualTo(departmentDto.getId());
        Assertions.assertThat(actualDepartmentDtoResponse.getName()).isEqualTo(departmentDto.getName());
    }
}
