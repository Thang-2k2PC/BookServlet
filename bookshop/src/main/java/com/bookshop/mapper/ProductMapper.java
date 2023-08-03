package com.bookshop.mapper;

import com.bookshop.entity.Nxb;
import com.bookshop.entity.Product;
import com.bookshop.service.NxbService;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Inject
    private NxbService nxbService;

    @Override
    public Product mapRow(ResultSet resultSet) {

        Product products = new Product();
        try {
            products.setId(resultSet.getLong("id"));
            products.setNameProduct(resultSet.getString("nameProduct"));
            products.setInfoProduct(resultSet.getString("infoProduct"));
            products.setPrice(resultSet.getLong("price"));
            products.setWareHouse(resultSet.getInt("wareHouse"));
            try {
                Nxb nxb = new Nxb();
                nxb.setId(resultSet.getLong("nxb_id"));
                nxb.setNameNxb(resultSet.getString("nameNxb"));
                products.setNxb(nxb);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
