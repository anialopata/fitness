package com.anialopata.controller;

import com.anialopata.api.v1.model.InstructorDTO;
import com.anialopata.service.InstructorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ania on 2018-08-01.
 */
public class InstructorControllerTest extends AbstractRestControllerTest {

    @Mock
    InstructorService instructorService;

    @InjectMocks
    InstructorController instructorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(instructorController).build();
    }

    @Test
    public void testListInstructors() throws Exception {

        InstructorDTO instructor1 = new InstructorDTO();
        instructor1.setFirstName("Ania");
        instructor1.setLastName("Lopata");
        instructor1.setInstructorUrl("/api/v1/instructors/1");

        InstructorDTO instructor2 = new InstructorDTO();
        instructor2.setFirstName("Adam");
        instructor2.setLastName("Dyrek");
        instructor2.setInstructorUrl("/api/v1/instructors/2");


        when(instructorService.getAllInstructors()).thenReturn(Arrays.asList(instructor1, instructor2));

        mockMvc.perform(get("/api/v1/instructors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.instructors", hasSize(2)));

    }

    @Test
    public void testGetInstructorById() throws Exception {

        InstructorDTO instructor1 = new InstructorDTO();
        instructor1.setFirstName("Ania");
        instructor1.setLastName("Lopata");
        instructor1.setInstructorUrl("/api/v1/instructors/1");

        when(instructorService.getInstructorById(anyLong())).thenReturn(instructor1);

        mockMvc.perform(get("/api/v1/instructors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Ania")));

    }

    @Test
    public void createNewInstructorTest() throws Exception {

        InstructorDTO instructor1 = new InstructorDTO();
        instructor1.setFirstName("Ania");
        instructor1.setLastName("Lopata");

        InstructorDTO returnDTO = new InstructorDTO();
        returnDTO.setFirstName(instructor1.getFirstName());
        returnDTO.setLastName(instructor1.getLastName());
        returnDTO.setInstructorUrl("/api/v1/instructors/1");

        when(instructorService.createInstructor(instructor1)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/instructors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(instructor1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Ania")))
                .andExpect(jsonPath("$.instructor_url", equalTo("/api/v1/instructors/1")));
    }

    @Test
    public void testUpdateInstructor() throws Exception {

        InstructorDTO instructor1 = new InstructorDTO();
        instructor1.setFirstName("Ania");
        instructor1.setLastName("Lopata");

        InstructorDTO returnDTO = new InstructorDTO();
        returnDTO.setFirstName(instructor1.getFirstName());
        returnDTO.setLastName(instructor1.getLastName());
        returnDTO.setInstructorUrl("/api/v1/instructors/1");

        when(instructorService.saveInstructorByDTO(anyLong(), any(InstructorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/instructors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(instructor1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Ania")))
                .andExpect(jsonPath("$.lastName", equalTo("Lopata")))
                .andExpect(jsonPath("$.instructor_url", equalTo("/api/v1/instructors/1")));
    }

    @Test
    public void testDeleteInstructor() throws Exception {

        mockMvc.perform(delete("/api/v1/instructors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(instructorService).deleteInstructor(anyLong());
    }


}