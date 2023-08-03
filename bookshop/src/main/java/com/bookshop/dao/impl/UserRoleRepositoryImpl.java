package com.bookshop.dao.impl;

import com.bookshop.dao.UserRoleRepository;
import com.bookshop.entity.UserRole;

public class UserRoleRepositoryImpl extends AbstractDAO<UserRole> implements UserRoleRepository {
    @Override
    public void delete(String user_id) {
        StringBuilder sql = new StringBuilder("DELETE FROM user_role ");
        sql.append(" WHERE user_id = ?");
        update(sql.toString(), user_id);
    }
}
