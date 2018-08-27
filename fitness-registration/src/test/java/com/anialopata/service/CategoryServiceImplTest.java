package com.anialopata.service;

import com.anialopata.api.v1.mapper.CategoryMapper;
import com.anialopata.api.v1.model.CategoryDTO;
import com.anialopata.domain.Category;
import com.anialopata.repository.CategoryRepository;
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
 * Created by Ania on 2018-08-03.
 */
public class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    public void getAllCategories() throws Exception {

        Category category = new Category();
        category.setName("cardio");
        category.setDescription("Intensywny trening całego ciała");

        Category category2 = new Category();
        category2.setName("cellulit killer");
        category2.setDescription("Trening spalający nadmiar tkanki tłuszczowej");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category,category2));

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        assertEquals(2, categoryDTOList.size());
    }

    @Test
    public void getCategoryById() throws Exception {

        Category category = new Category();
        category.setId(1L);
        category.setName("cardio");
        category.setDescription("Intensywny trening całego ciała");

        when(categoryRepository.findById(anyLong())).thenReturn((java.util.Optional.ofNullable(category)));

        CategoryDTO categoryDTO = categoryService.getCategoryById(1L);

        assertEquals("cardio", categoryDTO.getName());

    }

    @Test
    public void createNewCategory() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("cardio");

        Category category = new Category();
        category.setId(1L);
        category.setName(categoryDTO.getName());

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO savedDTO = categoryService.saveCategoryByDTO(1L, categoryDTO);

        assertEquals(categoryDTO.getName(), savedDTO.getName());
        assertEquals("/api/v1/categories/1", savedDTO.getCategoryUrl());
    }

    @Test
    public void deleteCategoryById() throws Exception {

        Long id = 1L;

        categoryRepository.deleteById(id);

        verify(categoryRepository, times(1)).deleteById(anyLong());
    }
}