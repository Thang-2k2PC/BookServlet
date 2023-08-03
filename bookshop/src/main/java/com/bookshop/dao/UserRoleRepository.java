package com.bookshop.dao;

import com.bookshop.entity.UserRole;

public interface UserRoleRepository  extends GenericDAO<UserRole> {

    void delete(String user_id);
}
