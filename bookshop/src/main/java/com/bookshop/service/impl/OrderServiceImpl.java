package com.bookshop.service.impl;

import com.bookshop.dao.OrderRepository;
import com.bookshop.entity.Order;
import com.bookshop.paging.PageOrder;
import com.bookshop.service.OrderService;

import javax.inject.Inject;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderRepository orderRepositoy;

    @Override
    public List<Order> getAllOrderByFilter(PageOrder pageble) {
        return orderRepositoy.findAllOrderByFilter(pageble);
    }

    @Override
    public int getTotalItem(PageOrder pageble) {
        return orderRepositoy.findTotalItem(pageble);
    }

    @Override
    public Order findOrderById(String order_id) {
        return orderRepositoy.findById(order_id);
    }

    @Override
    public Order findById(String order_id) {
        return orderRepositoy.findByOrder_id(order_id);
    }

    @Override
    public void update(Order order) {
        orderRepositoy.update(order);
    }

    @Override
    public Long save(Order order) {
        return orderRepositoy.save(order);
    }
//    @Override
//    public List<Order> findByStatusOrderAndShipper(String status, User shipper) {
////        return orderRepositoy.findByStatusOrderAndShipper(status, shipper);
//    }
}
