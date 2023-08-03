package com.bookshop.mapper;

import com.bookshop.entity.DetailsCart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailsCartsMapper implements RowMapper<DetailsCart> {
    @Override
    public DetailsCart mapRow(ResultSet resultSet) {
        DetailsCart detailsCart = new DetailsCart();
        try{
            detailsCart.setId(resultSet.getLong("id"));
            detailsCart.setQuantity(resultSet.getInt("quantity"));
            detailsCart.setCart_id(resultSet.getLong("cart_id"));
            detailsCart.setProduct_id(resultSet.getLong("product_id"));
            return detailsCart;
        }catch (SQLException e){
            return null;
        }
    }
}
