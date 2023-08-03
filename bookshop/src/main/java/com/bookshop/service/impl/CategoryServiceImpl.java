package com.bookshop.service.impl;

import com.bookshop.dao.CategoryRepository;
import com.bookshop.entity.Category;
import com.bookshop.paging.Pageble;
import com.bookshop.service.CategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByNameCategory(String nameCategory) {
        return categoryRepository.findCategoryByNameCategory(nameCategory);
    }

    @Override
    public List<Category> getAllCategoryByPageble(Pageble pageble) {
        return categoryRepository.findAllCategoryByPageble(pageble);
    }

    @Override
    public int getTotalItem() {
        return categoryRepository.getTotalItem();
    }

    @Override
    public void saveCategoryNew(Category categoryNew) {
        categoryRepository.save(categoryNew);
    }

    @Override
    public Category getCategoryById(String category_id) {
        return categoryRepository.findCategoryById(category_id);
    }

    @Override
    public void updateCategory(Category categoryUpdate) {
        categoryRepository.update(categoryUpdate);
    }

    @Override
    public void delete(String category_id) {
        categoryRepository.delete(category_id);
    }
}
