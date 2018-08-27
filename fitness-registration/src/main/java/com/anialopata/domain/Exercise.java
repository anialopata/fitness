package com.anialopata.domain;

import com.anialopata.api.v1.model.CustomerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ania on 2018-07-22.
 */

@Data
@EqualsAndHashCode(exclude = {"customers"})
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime date;

    @Lob
    private Byte[] image;

    @OneToOne(mappedBy = "exercise")
    private Category category;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @ManyToMany(mappedBy = "exercises")
    private Set<Customer> customers;

}
