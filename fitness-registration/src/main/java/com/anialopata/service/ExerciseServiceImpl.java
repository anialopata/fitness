package com.anialopata.service;

import com.anialopata.api.v1.mapper.ExerciseMapper;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.api.v1.model.ExerciseListDTO;
import com.anialopata.domain.Exercise;
import com.anialopata.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Ania on 2018-07-22.
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseMapper exerciseMapper;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseMapper exerciseMapper, ExerciseRepository exerciseRepository) {
        this.exerciseMapper = exerciseMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository
                .findAll()
                .stream()
                .map(exercise -> {
                    ExerciseDTO exerciseDTO = exerciseMapper.exerciseToExerciseDTO(exercise);
                    exerciseDTO.setExerciseUrl("/api/v1/exercises/" + exercise.getId());
                    return exerciseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseDTO getExerciseById(Long id) {
        return exerciseRepository
                .findById(id)
                .map(exerciseMapper::exerciseToExerciseDTO)
                .orElseThrow(RuntimeException::new);
    }

    private ExerciseDTO saveAndReturnDTO(Exercise exercise) {
        Exercise savedExercise = exerciseRepository.save(exercise);
        ExerciseDTO returnDto = exerciseMapper.exerciseToExerciseDTO(exercise);
        returnDto.setExerciseUrl("/api/v1/exercise/" + savedExercise.getId());

        return returnDto;
    }

    @Override
    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseMapper.exerciseDTOToExercise(exerciseDTO);
        Exercise saved = exerciseRepository.save(exercise);
        ExerciseDTO returned = exerciseMapper.exerciseToExerciseDTO(saved);
        returned.setExerciseUrl("/api/v1/exercises/" + saved.getId());

        return returned;
    }

    @Override
    public ExerciseDTO saveExerciseByDTO(Long id, ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseMapper.exerciseDTOToExercise(exerciseDTO);
        exercise.setId(id);
        return saveAndReturnDTO(exercise);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);

    }

    @Override
    public List<ExerciseDTO> getExerciseByDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);

        return exerciseRepository
                .findByDate(dateTime)
                .stream()
                .map(exerciseMapper::exerciseToExerciseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseDTO> getExerciseByDay(String day) {
        LocalDateTime startOfDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        LocalDateTime endOfDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(LocalTime.MAX);

        return exerciseRepository
                .findByDateIsBetween(startOfDay, endOfDay)
                .stream()
                .map(exerciseMapper::exerciseToExerciseDTO)
                .collect(Collectors.toList());
    }

}
