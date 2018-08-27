package com.anialopata.controller;

import com.anialopata.api.v1.model.CategoryDTO;
import com.anialopata.api.v1.model.CategoryListDTO;
import com.anialopata.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ania on 2018-07-22.
 */
@Api(description = "Category Controller")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "List all categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @ApiOperation(value = "Get Category by ID value")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById (@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @ApiOperation(value = "Create new Category")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }

    @ApiOperation(value = "Update existing Category")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategoryByDTO(id, categoryDTO);
    }

    @ApiOperation(value = "Delete Category")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
