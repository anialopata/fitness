package com.anialopata.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Ania on 2018-07-22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime date;

    private Byte[] image;

    private String role;

    @JsonProperty("exercise_url")
    private String exerciseUrl;
}
