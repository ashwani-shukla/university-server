package com.university.server.service.schedule;

import com.university.server.dto.ScheduleDto;
import com.university.server.exception.ResourceNotFoundException;
import com.university.server.model.Schedule;
import com.university.server.repository.IScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Schedule service.
 */
@Service
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Schedule service.
     *
     * @param scheduleRepository the schedule repository
     * @param modelMapper          the model mapper
     */
    public ScheduleService(IScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ScheduleDto> getSchedules() {
        return scheduleRepository.findAll().stream().map(schedule -> modelMapper.map(schedule, ScheduleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findByProfessorId(Integer professorId) throws ResourceNotFoundException {
        List<Schedule> scheduleList = scheduleRepository.findAllByProfessorId(professorId);
        if(scheduleList.isEmpty())
            throw new ResourceNotFoundException("Schedule not found with professor id: " + professorId);
        return scheduleList.stream().map(schedule -> modelMapper.map(schedule, ScheduleDto.class)).collect(Collectors.toList());
    }

    @Override
    public ScheduleDto saveSchedule(ScheduleDto scheduleDto) {
        Schedule scheduleRequest = modelMapper.map(scheduleDto, Schedule.class);
        return modelMapper.map(scheduleRepository.save(scheduleRequest), ScheduleDto.class);
    }

    @Override
    public ScheduleDto updateSchedule(Integer scheduleId, ScheduleDto scheduleDto) throws ResourceNotFoundException {
        return scheduleRepository.findById(scheduleId)
                .map(schedule -> {
                    Schedule scheduleRequest = modelMapper.map(scheduleDto, Schedule.class);
                    schedule.setId(scheduleRequest.getId());
                    schedule.setSemester(scheduleRequest.getSemester());
                    schedule.setYear(scheduleRequest.getYear());
                    return modelMapper.map(scheduleRepository.save(schedule), ScheduleDto.class);
                }).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + scheduleId));
    }

    @Override
    public void deleteSchedule(Integer scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        schedule.ifPresent(scheduleRepository::delete);
    }

}
