package com.bookshop.mapper;

import com.bookshop.entity.Category;
import com.bookshop.entity.Nxb;
import com.bookshop.entity.Product;
import com.bookshop.service.NxbService;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductMapper1 implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet) {

        Product products = new Product();
        try {
            products.setId(resultSet.getLong("id"));
            products.setNameProduct(resultSet.getString("nameProduct"));
            products.setWareHouse(resultSet.getInt("warehouse"));
            products.setInfoProduct(resultSet.getString("infoProduct"));
            products.setPrice(resultSet.getLong("price"));
            try{
                Category category = new Category();
                category.setId(resultSet.getLong("category_id"));
                category.setNameCategory(resultSet.getString("nameCategory"));

                products.setCategory(category);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                try{
                    Nxb nxb = new Nxb();
                    nxb.setId(resultSet.getLong("nxb_id"));
                    nxb.setNameNxb(resultSet.getString("nameNxb"));
                    products.setNxb(nxb);
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
            }

            return products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
