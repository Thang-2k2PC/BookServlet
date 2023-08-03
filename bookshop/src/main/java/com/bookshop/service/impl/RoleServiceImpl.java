package com.bookshop.service.impl;

import com.bookshop.dao.RoleRepository;
import com.bookshop.entity.Role;
import com.bookshop.service.RoleService;

import javax.inject.Inject;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Inject
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role findIdByRoleName(String role_name) {
        return roleRepository.findRoleByName(role_name);
    }

//    @Override
//    public Role findByName(String role_shipper) {
//        return roleRepository.findByName(role_shipper);
//    }


}
