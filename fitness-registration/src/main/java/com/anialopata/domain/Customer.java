package com.anialopata.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ania on 2018-07-22.
 */
@Data
@EqualsAndHashCode(exclude = {"exercises"})
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String phoneNumber;

    private String role;

    @ManyToMany
    @JoinTable(name = "customer_exercise",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name="exercise_id"))
    private Set<Exercise> exercises;

}
