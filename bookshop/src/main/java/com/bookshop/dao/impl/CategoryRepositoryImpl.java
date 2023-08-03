package com.bookshop.dao.impl;

import com.bookshop.dao.CategoryRepository;
import com.bookshop.entity.Category;
import com.bookshop.mapper.CategoryMapper;
import com.bookshop.mapper.ProductMapper1;
import com.bookshop.paging.Pageble;

import java.util.List;

public class CategoryRepositoryImpl extends AbstractDAO<Category> implements CategoryRepository {
    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        return query(sql, new CategoryMapper());
    }

    @Override
    public Category findCategoryByNameCategory(String nameCategory) {
        StringBuilder sql = new StringBuilder("SELECT * FROM categories ");
        sql.append(" WHERE nameCategory  LIKE '%" + nameCategory + "%'");
        List<Category> listCategory = query(sql.toString(), new CategoryMapper());
        return listCategory.isEmpty()?null : listCategory.get(0);
    }

    @Override
    public List<Category> findAllCategoryByPageble(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM categories ");
        if(pageble.getOffset() != null && pageble.getLimit() != null){
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");//Lay tu vi tri n-> m
        }
        return query(sql.toString(), new CategoryMapper(),pageble);
    }

    @Override
    public int getTotalItem() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS totalItem FROM categories");
        return count(sql.toString());
    }

    @Override
    public void save(Category categoryNew) {
        StringBuilder sql = new StringBuilder(" INSERT INTO categories(nameCategory) ");
        sql.append(" VALUES(?)");
        insert(sql.toString(), categoryNew.getNameCategory());
    }

    @Override
    public Category findCategoryById(String category_id) {
        StringBuilder sql = new StringBuilder(" SELECT * FROM categories");
        sql.append(" WHERE id = ?");
        List<Category> listCategory = query(sql.toString(), new CategoryMapper(),category_id);
        return listCategory.isEmpty()? null: listCategory.get(0);
    }

    @Override
    public void update(Category categoryUpdate) {
        StringBuilder sql = new StringBuilder("UPDATE categories");
        sql.append(" SET nameCategory = ? ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), categoryUpdate.getNameCategory(), categoryUpdate.getId());
    }

    @Override
    public void delete(String category_id) {
        StringBuilder sql = new StringBuilder("DELETE FROM categories ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), category_id);
    }


}
