package com.university.server.controller;

import com.university.server.dto.CourseDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.service.course.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * The type Course controller.
 */
@RestController
@RequestMapping("university/api/v1/")
@Validated
public class CourseController {

    private final CourseService courseService;

    /**
     * Instantiates a new Course controller.
     *
     * @param courseService the course service
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Gets courses.
     *
     * @return the courses
     */
    @GetMapping("/course")
    public ResponseEntity<List<CourseDto>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    /**
     * Gets course by id.
     *
     * @param courseId the course id
     * @return the course by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") Integer courseId) throws ResourceNotFoundException {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    /**
     * Create course response entity.
     *
     * @param courseDto the course dto
     * @return the response entity
     */
    @PostMapping("/course")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDto courseDto) {

        CourseDto courseResponse = courseService.saveCourse(courseDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(courseResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Update course response entity.
     *
     * @param courseId  the course id
     * @param courseDto the course dto
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/course/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer courseId,
                                              @Valid @RequestBody CourseDto courseDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
    }

    /**
     * Delete course response entity.
     *
     * @param courseId the course id
     * @return the response entity
     */
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }

}
