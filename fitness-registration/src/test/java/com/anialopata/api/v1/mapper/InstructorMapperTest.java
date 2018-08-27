package com.anialopata.api.v1.mapper;

import com.anialopata.api.v1.model.InstructorDTO;
import com.anialopata.domain.Instructor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ania on 2018-08-03.
 */
public class InstructorMapperTest {

    public static final String FIRSTNAME ="Ania";
    public static final String LASTNAME = "Lopata";
    InstructorMapper instructorMapper = InstructorMapper.INSTANCE;

    @Test
    public void instructorToInstructorDTO() throws Exception {

        Instructor instructor = new Instructor();
        instructor.setFirstName(FIRSTNAME);
        instructor.setLastName(LASTNAME);

        InstructorDTO instructorDTO = instructorMapper.instructorTOInstructorDTO(instructor);

        assertEquals(FIRSTNAME, instructorDTO.getFirstName());
        assertEquals(LASTNAME, instructorDTO.getLastName());
    }

    @Test
    public void instructorDTOToInstructor() throws Exception {

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setFirstName(FIRSTNAME);
        instructorDTO.setLastName(LASTNAME);

        Instructor instructor = instructorMapper.instructorDTOToInstructor(instructorDTO);

        assertEquals(FIRSTNAME, instructor.getFirstName());
        assertEquals(LASTNAME, instructor.getLastName());

    }
}