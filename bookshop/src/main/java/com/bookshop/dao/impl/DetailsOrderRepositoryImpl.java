package com.bookshop.dao.impl;

import com.bookshop.dao.DetailsOrderRepository;
import com.bookshop.entity.DetailsOrder;

public class DetailsOrderRepositoryImpl extends AbstractDAO<DetailsOrder> implements DetailsOrderRepository {
    @Override
    public void save(DetailsOrder detailsOrder) {
        StringBuilder sql = new StringBuilder("INSERT INTO detailsorders(price, order_id, product_id, quantity_order)");
        sql.append(" VALUES(?,?,?,?)");
        insert(sql.toString(), detailsOrder.getPrice(), detailsOrder.getOrder_id(),
                detailsOrder.getProduct_id(), detailsOrder.getQuantity_order());
    }
}
