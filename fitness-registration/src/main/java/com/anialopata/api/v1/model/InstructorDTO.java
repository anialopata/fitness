package com.anialopata.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Ania on 2018-07-29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO {

    private String firstName;

    private String lastName;

    private Set<ExerciseDTO> exercises;

    @JsonProperty("instructor_url")
    private String instructorUrl;
}
