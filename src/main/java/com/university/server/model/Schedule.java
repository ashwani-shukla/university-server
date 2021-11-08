package com.university.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;


/**
 * The type Schedule.
 */
@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Schedule implements Serializable {

    /**
     * The Id.
     */
    @EmbeddedId
    private ScheduleId id;

    /**
     * The Professor.
     */
    @ManyToOne
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    private Professor professor;

    /**
     * The Course.
     */
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * The Semester.
     */
    @Min(0)
    @Max(6)
    @Column(name = "semester")
    private Integer semester;

    /**
     * The Year.
     */
    @Column(name = "year")
    private Integer year;
}


