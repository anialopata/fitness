package com.anialopata.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-07-22.
 */
@Data
@EqualsAndHashCode(exclude = {"exercises"})
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy="instructor")
    private Set<Exercise> exercises;

}
