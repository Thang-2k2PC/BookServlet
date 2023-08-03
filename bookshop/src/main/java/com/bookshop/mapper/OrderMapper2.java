package com.bookshop.mapper;

import com.bookshop.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper2 implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) {
        Order orders = new Order();
        try {
            orders.setStatusOrder(resultSet.getString("statusOrder"));
            orders.setShipper_id(resultSet.getLong("shipper_id"));

            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
