package com.bookshop.service;

import com.bookshop.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    Role findIdByRoleName(String role_name);

//    Role findByName(String role_shipper);
}
