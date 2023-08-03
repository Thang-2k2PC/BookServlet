package com.bookshop.dao;

import com.bookshop.mapper.RowMapper;
import com.bookshop.paging.Pageble;

import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    int count(String sql, Object... parameters);

    void update(String sql, Object... parameters);

    Long insert(String sql, Object... parameters);

}
