package com.university.server.service.course;

import com.university.server.dto.CourseDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.model.Course;
import com.university.server.repository.ICourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Course service.
 */
@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Course service.
     *
     * @param courseRepository the course repository
     * @param modelMapper          the model mapper
     */
    public CourseService(ICourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseDto> getCourses() {
        return courseRepository.findAll().stream().map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(Integer courseId) throws ResourceNotFoundException {
        Optional<Course> courseList = courseRepository.findById(courseId);
        if(courseList.isEmpty())
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        return modelMapper.map(courseList.get(), CourseDto.class);
    }

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        Course courseRequest = modelMapper.map(courseDto, Course.class);
        return modelMapper.map(courseRepository.save(courseRequest), CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(Integer courseId, CourseDto courseDto) throws ResourceNotFoundException {
        return courseRepository.findById(courseId)
                .map(course -> {
                    Course courseRequest = modelMapper.map(courseDto, Course.class);
                    course.setName(courseRequest.getName());
                    course.setDepartment(courseRequest.getDepartment());
                    course.setCredits(courseRequest.getCredits());
                    return modelMapper.map(courseRepository.save(course), CourseDto.class);
                }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    }

    @Override
    public void deleteCourse(Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        course.ifPresent(courseRepository::delete);
    }

}
