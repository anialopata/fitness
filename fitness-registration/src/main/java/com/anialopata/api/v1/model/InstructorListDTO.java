package com.anialopata.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Ania on 2018-07-29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorListDTO {

    List<InstructorDTO> instructors;
}
