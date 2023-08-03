package com.bookshop.mapper;

import com.bookshop.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper1 implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) {
        Order orders = new Order();
        try {
            orders.setId(resultSet.getLong("id"));
            orders.setNameCustomer(resultSet.getString("nameCustomer"));
            orders.setStatusOrder(resultSet.getString("statusOrder"));
            orders.setSumPrice(resultSet.getLong("sumPrice"));
            orders.setDateOrder(resultSet.getTimestamp("dateOrder"));
            orders.setDateDelivery(resultSet.getTimestamp("dateDelivery"));
            orders.setDateReceive(resultSet.getTimestamp("dateReceive"));
            orders.setQuantity_order(resultSet.getLong("quantity_order"));
            orders.setPrice(resultSet.getLong("price"));
            orders.setUser_id_order(resultSet.getLong("user_id_order"));
            orders.setPhone(resultSet.getString("phone"));
            orders.setNote(resultSet.getString("note"));
            orders.setShipper_name(resultSet.getString("shipper_name"));
            orders.setProduct_id(resultSet.getLong("product_id"));
            orders.setAddressReceive(resultSet.getString("addressReceive"));
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
