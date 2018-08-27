package com.anialopata.api.v1.mapper;

import com.anialopata.api.v1.model.InstructorDTO;
import com.anialopata.domain.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Ania on 2018-07-29.
 */
@Mapper
public interface InstructorMapper {
    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);
    Instructor instructorDTOToInstructor(InstructorDTO instructorDTO);
    InstructorDTO instructorTOInstructorDTO(Instructor instructor);
}
