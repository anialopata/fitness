package com.anialopata.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

/**
 * Created by Ania on 2018-07-22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String phoneNumber;

    @JsonProperty("customer_url")
    private String customerUrl;

    private Set<ExerciseDTO> exercises;
}
