package com.bookshop.mapper;

import com.bookshop.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) {
        Order orders = new Order();
        try {
            orders.setStatusOrder(resultSet.getString("statusOrder"));
            orders.setDateDelivery(resultSet.getTimestamp("dateDelivery"));
            orders.setId(resultSet.getLong("id"));
            orders.setNameCustomer(resultSet.getString("nameCustomer"));
//            orders.setSumPrice(resultSet.getLong("sumPrice"));
            orders.setDateOrder(resultSet.getTimestamp("dateOrder"));
            orders.setDateReceive(resultSet.getTimestamp("dateReceive"));
            orders.setQuantity_order(resultSet.getLong("quantity_order"));
            orders.setPrice(resultSet.getLong("price"));

            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
