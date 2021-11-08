package com.university.server.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The type Schedule id.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ScheduleId implements Serializable {

    /**
     * The Professor id.
     */
    @Column(name = "professorId")
    @JsonIdentityReference(alwaysAsId = true)
    private Integer professorId;

    /**
     * The Course id.
     */
    @Column(name = "courseId")
    @JsonIdentityReference(alwaysAsId = true)
    private Integer courseId;
}


