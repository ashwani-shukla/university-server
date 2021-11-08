package com.university.server.dto;

import lombok.Data;


/**
 * The type Schedule dto.
 */
@Data
public class ScheduleDto {

    /**
     * The Id.
     */
    private Integer id;
    /**
     * The Professor id.
     */
    private Integer professorId;
    /**
     * The Course id.
     */
    private Integer courseId;
    /**
     * The Semester.
     */
    private Integer semester;
    /**
     * The Year.
     */
    private Integer year;
}


