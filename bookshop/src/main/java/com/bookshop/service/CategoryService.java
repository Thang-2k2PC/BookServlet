package com.bookshop.service;

import com.bookshop.entity.Category;
import com.bookshop.paging.Pageble;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category findCategoryByNameCategory(String nameCategory);

    List<Category> getAllCategoryByPageble(Pageble pageble);

    int getTotalItem();

    void saveCategoryNew(Category categoryNew);

    Category getCategoryById(String category_id);

    void updateCategory(Category categoryUpdate);

    void delete(String category_id);
}
