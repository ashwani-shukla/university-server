package com.university.server.dto;

import lombok.Data;


/**
 * The type Course dto.
 */
@Data
public class CourseDto
{
    /**
     * The Id.
     */
    private Integer id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Department id.
     */
    private Integer departmentId;
    /**
     * The Credits.
     */
    private Integer credits;
}
