package com.bookshop.mapper;

import com.bookshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) {
        User user = new User();
        try{
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setAddress(resultSet.getString("address"));
            user.setPhone(resultSet.getString("phone"));
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
