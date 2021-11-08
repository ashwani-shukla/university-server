package com.university.server.service.professor;

import com.university.server.dto.ProfessorCourseDto;
import com.university.server.dto.ProfessorCoursesDto;
import com.university.server.dto.ProfessorDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.model.Professor;
import com.university.server.repository.IProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Professor service.
 */
@Service
public class ProfessorService implements IProfessorService {

    private final IProfessorRepository professorRepository;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Professor service.
     *
     * @param professorRepository the professor repository
     * @param modelMapper          the model mapper
     */
    public ProfessorService(IProfessorRepository professorRepository, ModelMapper modelMapper) {
        this.professorRepository = professorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProfessorDto> getProfessors() {
        return professorRepository.findAll().stream().map(professor -> modelMapper.map(professor, ProfessorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfessorCoursesDto> getProfessorsWithCourses() {

        List<ProfessorCoursesDto> professorCourses = professorRepository.findAllProfessorAndCourses()
                .stream()
                .collect(Collectors.groupingBy(ProfessorCourseDto::getName, Collectors.mapping(ProfessorCourseDto::getCourse, Collectors.toList())))
                .entrySet()
                .stream()
                .map(entry -> new ProfessorCoursesDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return professorCourses;
    }

    @Override
    public ProfessorDto getProfessorById(Integer professorId) throws ResourceNotFoundException {
        Optional<Professor> professorList = professorRepository.findById(professorId);
        if(professorList.isEmpty())
            throw new ResourceNotFoundException("Professor not found with id: " + professorId);
        return modelMapper.map(professorList.get(), ProfessorDto.class);
    }

    @Override
    public ProfessorDto saveProfessor(ProfessorDto professorDto) {
        Professor professorRequest = modelMapper.map(professorDto, Professor.class);
        return modelMapper.map(professorRepository.save(professorRequest), ProfessorDto.class);
    }

    @Override
    public ProfessorDto updateProfessor(Integer professorId, ProfessorDto professorDto) throws ResourceNotFoundException {
        return professorRepository.findById(professorId)
                .map(professor -> {
                    Professor professorRequest = modelMapper.map(professorDto, Professor.class);
                    professor.setName(professorRequest.getName());
                    return modelMapper.map(professorRepository.save(professor), ProfessorDto.class);
                }).orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + professorId));
    }

    @Override
    public void deleteProfessor(Integer professorId) {
        Optional<Professor> professor = professorRepository.findById(professorId);
        professor.ifPresent(professorRepository::delete);
    }

}
