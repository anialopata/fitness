package com.anialopata.service;

import com.anialopata.api.v1.mapper.ExerciseMapper;
import com.anialopata.api.v1.mapper.InstructorMapper;
import com.anialopata.api.v1.model.InstructorDTO;
import com.anialopata.domain.Instructor;
import com.anialopata.repository.ExerciseRepository;
import com.anialopata.repository.InstructorRepository;
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
public class InstructorServiceImplTest {

    @Mock
    InstructorRepository instructorRepository;

    InstructorMapper instructorMapper = InstructorMapper.INSTANCE;

    InstructorService instructorService;

    ExerciseMapper exerciseMapper = ExerciseMapper.INSTANCE;

    ExerciseRepository exerciseRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        instructorService = new InstructorServiceImpl(instructorRepository, instructorMapper, exerciseRepository, exerciseMapper);
    }

    @Test
    public void getAllInstructors() throws Exception {

        Instructor instructor1 = new Instructor();
        instructor1.setId(1L);
        instructor1.setFirstName("Ania");
        instructor1.setLastName("Lopata");

        Instructor instructor2 = new Instructor();
        instructor2.setId(2L);
        instructor2.setFirstName("Adam");
        instructor2.setLastName("Dyrek");

        when(instructorRepository.findAll()).thenReturn(Arrays.asList(instructor1, instructor2));

        List<InstructorDTO> instructorDTOList = instructorService.getAllInstructors();

        assertEquals(2, instructorDTOList.size());

    }

    @Test
    public void getInstructorById() throws Exception {

        Instructor instructor1 = new Instructor();
        instructor1.setId(1L);
        instructor1.setFirstName("Ania");
        instructor1.setLastName("Lopata");

        when(instructorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(instructor1));

        InstructorDTO instructorDTO = instructorService.getInstructorById(1L);

        assertEquals("Ania", instructorDTO.getFirstName());
    }

    @Test
    public void createNewInstructor() throws Exception {

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setFirstName("Ania");

        Instructor savedInstructor = new Instructor();
        savedInstructor.setFirstName(instructorDTO.getFirstName());
        savedInstructor.setLastName(instructorDTO.getLastName());
        savedInstructor.setId(1L);

        when(instructorRepository.save(any(Instructor.class))).thenReturn(savedInstructor);

        InstructorDTO savedDTO = instructorService.saveInstructorByDTO(1L, instructorDTO);

        assertEquals(instructorDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals("/api/v1/instructor/1", savedDTO.getInstructorUrl());

    }

    @Test
    public void deleteInstructorById() throws Exception {

        Long id = 1L;

        instructorRepository.deleteById(id);

        verify(instructorRepository, times(1)).deleteById(anyLong());
    }
}