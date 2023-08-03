package com.bookshop.mapper;

import com.bookshop.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet) {
        try {
            Category category = new Category();
            category.setId(resultSet.getLong("id"));
            category.setNameCategory(resultSet.getString("nameCategory"));
            return category;
        } catch (SQLException e) {
            return null;
        }
    }
}
