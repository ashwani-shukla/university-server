package com.university.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * The type Professor course dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorCoursesDto {

    /**
     * The Name.
     */
    private String name;


    /**
     * The Course.
     */
    private List<String> course;
}
