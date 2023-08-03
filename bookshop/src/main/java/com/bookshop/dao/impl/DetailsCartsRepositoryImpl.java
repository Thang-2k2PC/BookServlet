package com.bookshop.dao.impl;

import com.bookshop.dao.DetailsCartsRepository;
import com.bookshop.entity.DetailsCart;
import com.bookshop.mapper.DetailsCartsMapper;

import java.util.List;

public class DetailsCartsRepositoryImpl extends AbstractDAO<DetailsCart> implements DetailsCartsRepository {
    @Override
    public DetailsCart getDetailCartByProductAndCart(long product_id, long cart_id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM detailscarts ");
        sql.append(" WHERE product_id = " + product_id + " AND cart_id = " + cart_id);
        List<DetailsCart> detailsCarts = query(sql.toString(), new DetailsCartsMapper());
        return detailsCarts.isEmpty() ? null : detailsCarts.get(0);
    }

    //    @Override
//    public Long save(DetailsCart detailsCart) {
//        StringBuilder sql = new StringBuilder("UPDATE detailscarts");
//        sql.append(" SET quantity = " + detailsCart.getQuantity());
//        sql.append(" WHERE  product_id = " + detailsCart.getProduct_id() + " AND cart_id = " + detailsCart.getCart_id());
//        return insert(sql.toString());
//    }
    @Override
    public void update(DetailsCart detailsCart) {
        StringBuilder sql = new StringBuilder("UPDATE detailscarts");
        sql.append(" SET quantity = ?");
        sql.append(" WHERE  product_id = ?");
        sql.append(" AND cart_id = ?");
        update(sql.toString(), detailsCart.getQuantity(), detailsCart.getProduct_id(), detailsCart.getCart_id());
    }


    @Override
    public List<DetailsCart> findDetailsCartByCart(long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM detailscarts ");
        sql.append("WHERE cart_id = " + id);
        List<DetailsCart> detailsCartList = query(sql.toString(), new DetailsCartsMapper());
        return detailsCartList;
    }

    @Override
    public void delete(DetailsCart detailsCart) {
        StringBuilder sql = new StringBuilder("DELETE FROM detailscarts ");
        sql.append(" WHERE  product_id = ?" + " AND cart_id = ?");
        update(sql.toString(), detailsCart.getProduct_id(), detailsCart.getCart_id());
    }

    @Override
    public void updateDetailCart(DetailsCart detailsCart) {
        StringBuilder sql = new StringBuilder("INSERT INTO detailscarts(quantity, cart_id, product_id)");
        sql.append(" VALUES(?,?,?)");
        insert(sql.toString(), detailsCart.getQuantity(), detailsCart.getCart_id(), detailsCart.getProduct_id());
    }

    @Override
    public void deleteAllDetailsCarts(Long id) {
        StringBuilder sql = new StringBuilder("DELETE FROM detailscarts WHERE cart_id = ?");
        update(sql.toString(), id);
    }
}
