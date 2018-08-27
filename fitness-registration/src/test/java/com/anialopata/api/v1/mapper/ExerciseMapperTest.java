package com.anialopata.api.v1.mapper;

import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.domain.Exercise;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ania on 2018-08-03.
 */
public class ExerciseMapperTest {

    public static final String NAME = "brzuch";
    ExerciseMapper exerciseMapper = ExerciseMapper.INSTANCE;

    @Test
    public void exerciseToExerciseDTO() throws Exception {

        Exercise ex = new Exercise();
        ex.setName(NAME);

        ExerciseDTO exerciseDTO = exerciseMapper.exerciseToExerciseDTO(ex);

        assertEquals(NAME, exerciseDTO.getName());
    }

    @Test
    public void exerciseDTOToExercise() throws Exception {

        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setName(NAME);

        Exercise exercise = exerciseMapper.exerciseDTOToExercise(exerciseDTO);

        assertEquals(NAME, exercise.getName());
    }
}