package com.anialopata.service;

import com.anialopata.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by Ania on 2018-07-22.
 */
public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO saveCategoryByDTO(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
