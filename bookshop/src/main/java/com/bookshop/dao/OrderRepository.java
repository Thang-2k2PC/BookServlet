package com.bookshop.dao;

import com.bookshop.entity.Order;
import com.bookshop.paging.PageOrder;

import java.util.List;

public interface OrderRepository extends GenericDAO<Order>  {
    List<Order> findAllOrderByFilter(PageOrder pageble);

    int findTotalItem(PageOrder pageble);

    Order findById(String order_id);

    Order findByOrder_id(String order_id);
    void update(Order order);

    Long save(Order order);
//    List<Order> findByStatusOrderAndShipper(String status, User shipper);
}
