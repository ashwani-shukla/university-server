package com.university.server.model;

import com.fasterxml.jackson.annotation.*;
import com.university.server.dto.ProfessorCourseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * The type Professor.
 */
@Entity


@NamedNativeQuery(
        name = "find_professor_course_dto",
        query = "SELECT p.name, c.name AS course FROM Professor p " +
                "INNER JOIN Schedule s ON p.professor_id=s.professor_id " +
                "INNER JOIN Course c ON c.course_id=s.course_id",
        resultSetMapping = "professor_course_dto"
)
@SqlResultSetMapping(
        name = "professor_course_dto",
        classes = @ConstructorResult(
                targetClass = ProfessorCourseDto.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "course", type = String.class)
                }
        )
)
@Table(name = "professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Professor implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id", nullable = false)
    private Integer id;

    /**
     * The Name.
     */
    @NotBlank(message = "Professor must have a name")
    @Size(min = 1, max = 255, message = "Professor name should not be more than 255 chars")
    @Column(name = "name")
    private String name;

    /**
     * The Department.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "department_id"))
    @JsonIdentityReference(alwaysAsId = true)
    private Department department;

    /**
     * The Schedule.
     */
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Schedule> schedule;
}
