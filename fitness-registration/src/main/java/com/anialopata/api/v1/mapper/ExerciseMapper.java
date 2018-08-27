package com.anialopata.api.v1.mapper;

import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.domain.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Ania on 2018-07-22.
 */
@Mapper
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);
    ExerciseDTO exerciseToExerciseDTO(Exercise exercise);
    Exercise exerciseDTOToExercise(ExerciseDTO exerciseDTO);
}
