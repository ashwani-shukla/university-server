package com.university.server.dto;

import lombok.Data;


/**
 * The type Professor dto.
 */
@Data
public class ProfessorDto {

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
}
