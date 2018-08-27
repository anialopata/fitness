package com.anialopata.service;

import com.anialopata.api.v1.mapper.ExerciseMapper;
import com.anialopata.api.v1.mapper.InstructorMapper;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.api.v1.model.InstructorDTO;
import com.anialopata.domain.Exercise;
import com.anialopata.domain.Instructor;
import com.anialopata.repository.ExerciseRepository;
import com.anialopata.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ania on 2018-07-29.
 */
@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper, ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository
                .findAll()
                .stream()
                .map(instructor -> {
                    InstructorDTO instructorDTO = instructorMapper.instructorTOInstructorDTO(instructor);
                    instructorDTO.setInstructorUrl("/api/v1/instructors/" + instructor.getId());
                    return instructorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public InstructorDTO getInstructorById(Long id) {

        return instructorRepository.findById(id)
                .map(instructorMapper::instructorTOInstructorDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {

        Instructor instructor = instructorMapper.instructorDTOToInstructor(instructorDTO);
        Instructor saved = instructorRepository.save(instructor);
        InstructorDTO returned = instructorMapper.instructorTOInstructorDTO(saved);
        returned.setInstructorUrl("/api/v1/instructor/" + saved.getId());

        return returned;
    }
    private InstructorDTO saveAndReturnDTO(Instructor instructor) {
        Instructor saved = instructorRepository.save(instructor);
        InstructorDTO returnDto = instructorMapper.instructorTOInstructorDTO(instructor);
        returnDto.setInstructorUrl("/api/v1/instructor/" + saved.getId());

        return returnDto;
    }

    @Override
    public InstructorDTO saveInstructorByDTO(Long id, InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.instructorDTOToInstructor(instructorDTO);
        instructor.setId(id);

        return saveAndReturnDTO(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public Set<ExerciseDTO> findInstructorExercises(Long id) {

        InstructorDTO instructorDTO = getInstructorById(id);
        Instructor instructor = instructorMapper.instructorDTOToInstructor(instructorDTO);
        InstructorDTO returned = instructorMapper.instructorTOInstructorDTO(instructor);

        return returned.getExercises();
    }

    @Override
    public ExerciseDTO addExerciseToInstructor(Long instructorId, ExerciseDTO exerciseDTO) {

        Instructor instructor = instructorRepository.getOne(instructorId);
        if (instructor.getExercises() == null) {
            instructor.setExercises(new HashSet<>());
        }
        Exercise ex = exerciseMapper.exerciseDTOToExercise(exerciseDTO);
        exerciseRepository.save(ex);
        instructor.getExercises().add(ex);
        instructorRepository.save(instructor);

        return exerciseMapper.exerciseToExerciseDTO(ex);
    }
}

