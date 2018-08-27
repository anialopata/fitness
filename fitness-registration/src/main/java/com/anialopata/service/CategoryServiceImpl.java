package com.anialopata.service;

import com.anialopata.api.v1.mapper.CategoryMapper;
import com.anialopata.api.v1.model.CategoryDTO;
import com.anialopata.domain.Category;
import com.anialopata.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ania on 2018-07-22.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository
                .findAll()
                .stream()
                .map(category -> {
                    CategoryDTO categoryDTO= categoryMapper.categoryToCategoryDTO(category);
                    categoryDTO.setCategoryUrl("/api/v1/categories/" + category.getId());
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {

        return categoryRepository
                .findById(id)
                .map(categoryMapper::categoryToCategoryDTO)
                .orElseThrow(RuntimeException::new);
    }

    private CategoryDTO saveAndReturnDTO(Category category) {
        Category saved = categoryRepository.save(category);
        CategoryDTO returnDto = categoryMapper.categoryToCategoryDTO(saved);
        returnDto.setCategoryUrl("/api/v1/categories/" + saved.getId());

        return returnDto;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        return saveAndReturnDTO(categoryMapper.categoryDTOToCategory(categoryDTO));
    }

    @Override
    public CategoryDTO saveCategoryByDTO(Long id, CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        category.setId(id);

        return saveAndReturnDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
