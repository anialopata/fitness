package com.anialopata.api.v1.mapper;

import com.anialopata.api.v1.model.CategoryDTO;
import com.anialopata.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ania on 2018-08-03.
 */
public class CategoryMapperTest {

    public static final String NAME = "cardio";
    public static final String DESCRIPTION = "Intensywny trening całego ciała";
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        Category category= new Category();
        category.setName(NAME);
        category.setDescription(DESCRIPTION);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(DESCRIPTION, categoryDTO.getDescription());
    }

    @Test
    public void categoryDTOToCategory() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);
        categoryDTO.setDescription(DESCRIPTION);

        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

        assertEquals(NAME, category.getName());
        assertEquals(DESCRIPTION, category.getDescription());

    }
}