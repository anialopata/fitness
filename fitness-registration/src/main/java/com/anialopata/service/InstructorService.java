package com.anialopata.service;

import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.api.v1.model.InstructorDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-07-29.
 */
public interface InstructorService {
    List<InstructorDTO> getAllInstructors();
    InstructorDTO getInstructorById(Long id);
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    InstructorDTO saveInstructorByDTO(Long id, InstructorDTO instructorDTO);
    void deleteInstructor(Long id);
    Set<ExerciseDTO> findInstructorExercises(Long id);
    ExerciseDTO addExerciseToInstructor(Long instructorId, ExerciseDTO exerciseDTO);
}


