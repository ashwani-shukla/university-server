package com.university.server.repository;

import com.university.server.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Schedule repository.
 */
@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value="select s from Schedule s where s.professor.id=?1")
    List<Schedule> findAllByProfessorId(Integer professorId);
}