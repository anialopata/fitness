package com.anialopata.controller;

import com.anialopata.api.v1.model.CategoryDTO;
import com.anialopata.service.CategoryService;
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
 * Created by Ania on 2018-08-03.
 */
public class CategoryControllerTest extends AbstractRestControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void listCategories() throws Exception {

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName("cardio");
        categoryDTO1.setDescription("Intensywny trening całego ciała");
        categoryDTO1.setCategoryUrl("/api/v1/categories/1");

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName("cellulit killer");
        categoryDTO2.setDescription("Trening spalający nadmiar tkanki tłuszczowej");
        categoryDTO2.setCategoryUrl("/api/v1/categories/1");

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(categoryDTO1, categoryDTO2));

        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void getCategoryById() throws Exception {

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName("cardio");
        categoryDTO1.setDescription("Intensywny trening całego ciała");
        categoryDTO1.setCategoryUrl("/api/v1/categories/1");

        when(categoryService.getCategoryById(anyLong())).thenReturn(categoryDTO1);

        mockMvc.perform(get("/api/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("cardio")));
    }

    @Test
    public void createNewCategory() throws Exception {

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName("cardio");
        categoryDTO1.setDescription("Intensywny trening całego ciała");
        categoryDTO1.setCategoryUrl("/api/v1/categories/1");

        CategoryDTO returnDTO = new CategoryDTO();
        returnDTO.setName(categoryDTO1.getName());
        returnDTO.setDescription(categoryDTO1.getDescription());
        returnDTO.setCategoryUrl(categoryDTO1.getCategoryUrl());

        when(categoryService.createCategory(categoryDTO1)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoryDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("cardio")))
                .andExpect(jsonPath("$.description", equalTo("Intensywny trening całego ciała")))
                .andExpect(jsonPath("$.category_url", equalTo("/api/v1/categories/1")));
    }

    @Test
    public void updateCategory() throws Exception {

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName("cardio");
        categoryDTO1.setDescription("Intensywny trening całego ciała");
        categoryDTO1.setCategoryUrl("/api/v1/categories/1");

        CategoryDTO returnDTO = new CategoryDTO();
        returnDTO.setName(categoryDTO1.getName());
        returnDTO.setDescription(categoryDTO1.getDescription());
        returnDTO.setCategoryUrl(categoryDTO1.getCategoryUrl());

        when(categoryService.saveCategoryByDTO(anyLong(), any(CategoryDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoryDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("cardio")))
                .andExpect(jsonPath("$.description", equalTo("Intensywny trening całego ciała")))
                .andExpect(jsonPath("$.category_url", equalTo("/api/v1/categories/1")));
    }

    @Test
    public void deleteCategory() throws Exception {

        mockMvc.perform(delete("/api/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService).deleteCategory(anyLong());
    }
}