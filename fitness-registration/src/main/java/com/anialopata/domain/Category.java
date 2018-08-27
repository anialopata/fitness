package com.anialopata.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Ania on 2018-07-22.
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

}
