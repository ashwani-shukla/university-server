package com.university.server.service.schedule;

import com.university.server.dto.ScheduleDto;
import com.university.server.exception.ResourceNotFoundException;

import java.util.List;


/**
 * The interface Schedule service.
 */
public interface IScheduleService {

    /**
     * Gets schedules.
     *
     * @return the schedules
     */
    List<ScheduleDto> getSchedules();

    /**
     * Find by professor id list.
     *
     * @param professorId the professor id
     * @return the list
     */
    List<ScheduleDto> findByProfessorId(Integer professorId) throws ResourceNotFoundException;

    /**
     * Save schedule.
     *
     * @param scheduleDtoRequest the schedule dto request
     * @return the schedule
     */
    ScheduleDto saveSchedule(ScheduleDto scheduleDtoRequest);

    /**
     * Update schedule.
     *
     * @param scheduleId         the schedule id
     * @param scheduleDtoRequest the schedule dto request
     * @return the schedule
     * @throws ResourceNotFoundException the resource not found exception
     */
    ScheduleDto updateSchedule(Integer scheduleId, ScheduleDto scheduleDtoRequest) throws ResourceNotFoundException;

    /**
     * Delete schedule.
     *
     * @param scheduleId the schedule id
     */
    void deleteSchedule(Integer scheduleId);
}
