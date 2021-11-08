package com.university.server.controller;

import com.university.server.dto.ScheduleDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.service.schedule.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * The type Schedule controller.
 */
@RestController
@RequestMapping("university/api/v1/")
@Validated
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * Instantiates a new Schedule controller.
     *
     * @param scheduleService the schedule service
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Gets schedules.
     *
     * @return the schedules
     */
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleDto>> getSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    /**
     * Gets schedule by professor id.
     *
     * @param professorId the professor id
     * @return the schedule by professor id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @RequestMapping(value = "/schedule", params = "professorId")
    public ResponseEntity<?> getScheduleByProfessorId(@RequestParam() Integer professorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(scheduleService.findByProfessorId(professorId));
    }

    /**
     * Create schedule response entity.
     *
     * @param scheduleDto the schedule dto
     * @return the response entity
     */
    @PostMapping("/schedule")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleDto scheduleDto) {

        ScheduleDto scheduleResponse = scheduleService.saveSchedule(scheduleDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(scheduleResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Update schedule response entity.
     *
     * @param scheduleId  the schedule id
     * @param scheduleDto the schedule dto
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer scheduleId,
                                              @Valid @RequestBody ScheduleDto scheduleDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, scheduleDto));
    }

    /**
     * Delete schedule response entity.
     *
     * @param scheduleId the schedule id
     * @return the response entity
     */
    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Integer scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok().build();
    }

}
