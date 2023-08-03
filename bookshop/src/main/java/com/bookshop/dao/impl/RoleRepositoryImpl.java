package com.bookshop.dao.impl;

import com.bookshop.dao.RoleRepository;
import com.bookshop.entity.Role;
import com.bookshop.mapper.RoleMapper;

import java.util.List;

public class RoleRepositoryImpl extends AbstractDAO<Role> implements RoleRepository {
    @Override
    public Role findRoleByName(String roleName) {
        StringBuilder sql = new StringBuilder("SELECT * FROM roles ");
        sql.append(" WHERE name = '" + roleName + "'");
        List<Role> list = query(sql.toString(), new RoleMapper());
        return list.isEmpty()? null : list.get(0);
    }

    @Override
    public List<Role> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM roles");
        return query(sql.toString(), new RoleMapper());
    }

//    @Override
//    public Role findByName(String role_shipper) {
//        StringBuilder sql = new StringBuilder("SELECT * FROM roles");
//        sql.append(" WHERE name = ?");
//        List<Role> listRole = query(sql.toString(), new RoleMapper(), role_shipper);
//        return listRole.isEmpty()?null : listRole.get(0);
//    }
}
