package com.anialopata.service;

import com.anialopata.api.v1.model.ExerciseDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Ania on 2018-07-22.
 */
public interface ExerciseService {
    List<ExerciseDTO> getAllExercises();

    ExerciseDTO getExerciseById(Long id);

    ExerciseDTO createExercise(ExerciseDTO exerciseDTO);

    ExerciseDTO saveExerciseByDTO(Long id, ExerciseDTO exerciseDTO);

    void deleteExercise(Long id);

    List<ExerciseDTO> getExerciseByDate(String date);

    List<ExerciseDTO> getExerciseByDay(String day);
//
//    ExerciseDTO getExerciseByDate(Date date);
//
//    ExerciseDTO getExerciseByDateIsBefore(String date);

//    ExerciseDTO getExerciseByDate(String date);

//    ExerciseDTO getExerciseByDay(String day);
}
