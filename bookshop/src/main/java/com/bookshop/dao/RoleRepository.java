package com.bookshop.dao;

import com.bookshop.entity.Role;

import java.util.List;

public interface RoleRepository extends GenericDAO<Role> {
    Role findRoleByName(String roleName);

    List<Role> findAll();

//    Role findByName(String role_shipper);
}
