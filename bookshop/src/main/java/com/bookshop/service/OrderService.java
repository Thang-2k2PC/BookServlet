package com.bookshop.service;

import com.bookshop.entity.Order;
import com.bookshop.entity.User;
import com.bookshop.paging.PageOrder;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrderByFilter(PageOrder pageble);

    int getTotalItem(PageOrder pageble);

    Order findOrderById(String order_id);

    Order findById(String order_id);

    void update(Order order);

    Long save(Order order);
//    List<Order> findByStatusOrderAndShipper(String status, User shipper);
}
