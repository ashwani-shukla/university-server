package com.university.server.service.course;

import com.university.server.dto.CourseDto;
import com.university.server.exception.ResourceNotFoundException;

import java.util.List;


/**
 * The interface Course service.
 */
public interface ICourseService {

    /**
     * Gets courses.
     *
     * @return the courses
     */
    List<CourseDto> getCourses();

    /**
     * Gets course by id.
     *
     * @param courseId the course id
     * @return the course by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    CourseDto getCourseById(Integer courseId) throws ResourceNotFoundException;

    /**
     * Save course.
     *
     * @param courseDtoRequest the course dto request
     * @return the course
     */
    CourseDto saveCourse(CourseDto courseDtoRequest);

    /**
     * Update course.
     *
     * @param courseId         the course id
     * @param courseDtoRequest the course dto request
     * @return the course
     * @throws ResourceNotFoundException the resource not found exception
     */
    CourseDto updateCourse(Integer courseId, CourseDto courseDtoRequest) throws ResourceNotFoundException;

    /**
     * Delete course.
     *
     * @param courseId the course id
     */
    void deleteCourse(Integer courseId);
}
