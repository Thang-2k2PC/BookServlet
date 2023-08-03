package com.bookshop.mapper;

import com.bookshop.entity.Role;
import com.bookshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAndRole_TotalItemsMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) {
        try{
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setAddress(resultSet.getString("address"));
            user.setPhone(resultSet.getString("phone"));
            user.setTotalItems(resultSet.getInt("totalItems"));


            try{
                Role role = new Role();
                role.setId(resultSet.getLong("role_id"));
                role.setName(resultSet.getString("name"));
                user.setRole(role);
            }catch (SQLException e1){
                System.out.println(e1.getMessage());
            }

            return user;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
