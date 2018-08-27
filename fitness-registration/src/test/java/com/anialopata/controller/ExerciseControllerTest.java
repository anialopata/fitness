package com.anialopata.controller;

import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.service.ExerciseService;
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
public class ExerciseControllerTest extends AbstractRestControllerTest {

    @Mock
    ExerciseService exerciseService;

    @InjectMocks
    ExerciseController exerciseController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(exerciseController).build();
    }

    @Test
    public void testListCustomers() throws Exception {

        ExerciseDTO ex1 = new ExerciseDTO();
        ex1.setName("brzuch");
        ex1.setExerciseUrl("/api/v1/exercises/1");

        ExerciseDTO ex2 = new ExerciseDTO();
        ex2.setName("plecy");
        ex2.setExerciseUrl("/api/v1/exercises/2");

        when(exerciseService.getAllExercises()).thenReturn(Arrays.asList(ex1, ex2));

        mockMvc.perform(get("/api/v1/exercises")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exercises", hasSize(2)));

    }

    @Test
    public void testGetExerciseById() throws Exception {

        ExerciseDTO ex1 = new ExerciseDTO();
        ex1.setName("brzuch");
        ex1.setExerciseUrl("/api/v1/exercises/1");

        when(exerciseService.getExerciseById(anyLong())).thenReturn(ex1);

        mockMvc.perform(get("/api/v1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("brzuch")));

    }

    @Test
    public void createNewCustomerTest() throws Exception {

        ExerciseDTO ex1 = new ExerciseDTO();
        ex1.setName("brzuch");
        ex1.setExerciseUrl("/api/v1/exercises/1");

        ExerciseDTO returnDTO = new ExerciseDTO();
        returnDTO.setName(ex1.getName());
        returnDTO.setExerciseUrl("/api/v1/exercises/1");

        when(exerciseService.createExercise(ex1)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ex1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("brzuch")))
                .andExpect(jsonPath("$.exercise_url", equalTo("/api/v1/exercises/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        ExerciseDTO ex1 = new ExerciseDTO();
        ex1.setName("brzuch");
        ex1.setExerciseUrl("/api/v1/exercises/1");

        ExerciseDTO returnDTO = new ExerciseDTO();
        returnDTO.setName(ex1.getName());
        returnDTO.setExerciseUrl("/api/v1/exercises/1");

        when(exerciseService.saveExerciseByDTO(anyLong(), any(ExerciseDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ex1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("brzuch")))
                .andExpect(jsonPath("$.exercise_url", equalTo("/api/v1/exercises/1")));
    }

    @Test
    public void testDeleteExercise() throws Exception {

        mockMvc.perform(delete("/api/v1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(exerciseService).deleteExercise(anyLong());
    }


}