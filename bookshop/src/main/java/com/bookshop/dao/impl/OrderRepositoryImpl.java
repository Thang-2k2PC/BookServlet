package com.bookshop.dao.impl;

import com.bookshop.dao.OrderRepository;
import com.bookshop.entity.Order;
import com.bookshop.mapper.OrderMapper;
import com.bookshop.mapper.OrderMapper1;
import com.bookshop.mapper.OrderMapper2;
import com.bookshop.paging.PageOrder;

import java.util.List;

public class OrderRepositoryImpl extends AbstractDAO<Order> implements OrderRepository {
    @Override
    public List<Order> findAllOrderByFilter(PageOrder pageble) {
        StringBuilder sql = new StringBuilder("SELECT  o.id, nameCustomer, statusOrder, dateOrder, dateDelivery, dateReceive , d.quantity_order, d.price");
        sql.append(" FROM orders AS o");
        sql.append(" INNER JOIN detailsorders AS d ON d.order_id = o.id");
        sql.append(" WHERE statusOrder = ?");
        if(!pageble.searchOrderDto().getFromDate().isEmpty() && !pageble.searchOrderDto().getToDate().isEmpty()){
            sql.append(" AND dateOrder >= '" + pageble.searchOrderDto().getFromDate() + "'  AND  dateOrder <= '" + pageble.searchOrderDto().getToDate() + "'" );
        }
        if(pageble.getOffset() != null && pageble.getLimit() != null){
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");//Lay tu vi tri n-> m
        }
        return query(sql.toString(), new OrderMapper(), pageble.searchOrderDto().getStatusOrder());
    }

    @Override
    public int findTotalItem(PageOrder pageble) {
        StringBuilder sql = new StringBuilder(" SELECT COUNT(*) AS totalItems ");
        sql.append(" FROM(");
        sql.append(" SELECT o.id, nameCustomer, statusOrder, dateOrder, dateDelivery, dateReceive, d.quantity_order, d.price");
        sql.append(" FROM orders AS o ");
        sql.append(" INNER JOIN detailsorders AS d ON d.order_id = o.id");
        sql.append("  WHERE statusOrder = ?");
        if(!pageble.searchOrderDto().getFromDate().isEmpty() && !pageble.searchOrderDto().getToDate().isEmpty()){
            sql.append(" AND dateOrder >= '" + pageble.searchOrderDto().getFromDate() + "'  AND  dateOrder <= '" + pageble.searchOrderDto().getToDate() + "'" );
        }
        sql.append(" ) AS subquery ");
        return count(sql.toString(), pageble.searchOrderDto().getStatusOrder());
    }

    @Override
    public Order findById(String order_id) {
        StringBuilder sql = new StringBuilder("SELECT  o.id, nameCustomer, user_id_order,o.phone,note, addressReceive, sumPrice, statusOrder, dateOrder, dateDelivery, dateReceive, d.quantity_order, d.price,d.product_id, u.username AS shipper_name");
        sql.append(" FROM orders AS o ");
        sql.append(" INNER JOIN detailsorders AS d ON d.order_id = o.id");
//        sql.append(" INNER JOIN products AS p ON p.id = d.product_id");
        sql.append(" INNER JOIN users AS u ON u.id = o.shipper_id ");
        sql.append(" WHERE o.id = ?");
        List<Order> listOrder = query(sql.toString(), new OrderMapper1(), order_id);
        return listOrder.isEmpty()? null: listOrder.get(0);
    }

    @Override
    public Order findByOrder_id(String order_id) {
        StringBuilder sql = new StringBuilder("SELECT statusOrder, shipper_id ");
        sql.append(" FROM orders");
        sql.append(" WHERE id = ?");
        List<Order> listOrder = query(sql.toString(), new OrderMapper2(), order_id);
        return listOrder.isEmpty()? null: listOrder.get(0);
    }

    @Override
    public void update(Order order) {
        StringBuilder sql = new StringBuilder("UPDATE orders ");
        sql.append(" SET statusOrder = '" + order.getStatusOrder() + "'" );
        if(order.getDateDelivery() != null){
            sql.append(" ,dateDelivery = '" + order.getDateDelivery() + "'" );
        }
        if(order.getShipper_id() != 0){
            sql.append(" ,shipper_id = " + order.getShipper_id());
        }
        sql.append(" WHERE id = " + order.getId());
        update(sql.toString());
    }

    @Override
    public Long save(Order order) {
        StringBuilder sql = new StringBuilder("INSERT INTO orders (addressReceive, nameCustomer, dateOrder, phone, statusOrder, sumPrice ");
        if(order.getUser_id_order() != 0){
            sql.append(" , user_id_order)");
            sql.append(" VALUES(?,?,?,?,?,?,?)");
        }else{
            sql.append(" )");
            sql.append(" VALUES(?,?,?,?,?,?)");
        }

        return insert(sql.toString(), order.getAddressReceive(), order.getNameCustomer(),order.getDateOrder(), order.getPhone(), order.getStatusOrder(),order.getSumPrice(), order.getUser_id_order());
    }
//    @Override
//    public List<Order> findByStatusOrderAndShipper(String status, User shipper) {
//        StringBuilder sql = new StringBuilder(" SELECT u.id, email, username, o.id, shipper_id, o.id AS order_id ");
//        sql.append("FROM users AS u ");
//        sql.append(" INNER JOIN orders AS o ON o.shipper_id = u.id");
//        sql.append(" WHERE statusOrder = ? AND shipper_id = ?");
//        return query(sql.toString(), new OrderMapper());
//    }
}
