package com.anialopata.service;

import com.anialopata.api.v1.mapper.ExerciseMapper;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.domain.Exercise;
import com.anialopata.repository.ExerciseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ania on 2018-08-01.
 */
public class ExerciseServiceImplTest {

    @Mock
    ExerciseRepository exerciseRepository;

    ExerciseMapper exerciseMapper = ExerciseMapper.INSTANCE;

    ExerciseService exerciseService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        exerciseService = new ExerciseServiceImpl(exerciseMapper, exerciseRepository);
    }

    @Test
    public void getAllExercises() throws Exception {

        Exercise ex1 = new Exercise();
        ex1.setId(1L);
        ex1.setName("brzuch");

        Exercise ex2 = new Exercise();
        ex2.setId(2L);
        ex2.setName("plecy");

        when(exerciseRepository.findAll()).thenReturn(Arrays.asList(ex1, ex2));

        List<ExerciseDTO> exerciseDTOS = exerciseService.getAllExercises();

        assertEquals(2, exerciseDTOS.size());

    }

    @Test
    public void getExerciseById() throws Exception {

        Exercise ex1 = new Exercise();
        ex1.setId(1L);
        ex1.setName("brzuch");

        when(exerciseRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(ex1));

        ExerciseDTO exerciseDTO = exerciseService.getExerciseById(1L);

        assertEquals("brzuch", exerciseDTO.getName());
    }

    @Test
    public void createNewExercise() throws Exception {

        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setName("brzuch");

        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setName(exerciseDTO.getName());

        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);

        ExerciseDTO savedDTO = exerciseService.saveExerciseByDTO(1L, exerciseDTO);

        assertEquals(exerciseDTO.getName(), savedDTO.getName());
        assertEquals("/api/v1/exercise/1", savedDTO.getExerciseUrl());

    }

    @Test
    public void deleteExerciseById() throws Exception {

        Long id = 1L;

        exerciseRepository.deleteById(id);

        verify(exerciseRepository, times(1)).deleteById(anyLong());
    }

}