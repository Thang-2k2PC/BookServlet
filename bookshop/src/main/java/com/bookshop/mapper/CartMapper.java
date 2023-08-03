package com.bookshop.mapper;

import com.bookshop.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements RowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet resultSet) {
        try{
            Cart cart = new Cart();
            cart.setId(resultSet.getLong("id"));
            cart.setSum_price(resultSet.getString("sum_price"));
            cart.setUser_id(resultSet.getLong("user_id"));
            return cart;
        }catch(SQLException e){
            return null;
        }

    }
}
