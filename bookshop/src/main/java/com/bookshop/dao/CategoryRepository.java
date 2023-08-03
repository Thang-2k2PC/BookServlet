package com.bookshop.dao;

import com.bookshop.entity.Category;
import com.bookshop.paging.Pageble;

import java.util.List;

public interface CategoryRepository extends GenericDAO<Category> {
    List<Category> findAll();

    Category findCategoryByNameCategory(String nameCategory);

    List<Category> findAllCategoryByPageble(Pageble pageble);

    int getTotalItem();

    void save(Category categoryNew);

    Category findCategoryById(String category_id);

    void update(Category categoryUpdate);

    void delete(String category_id);
}
