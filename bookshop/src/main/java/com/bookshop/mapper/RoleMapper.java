package com.bookshop.mapper;

import com.bookshop.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role>{
    @Override
    public Role mapRow(ResultSet resultSet) {
        try{
            Role role = new Role();
            role.setId(resultSet.getLong("id"));
            role.setName(resultSet.getString("name"));
            return role;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
