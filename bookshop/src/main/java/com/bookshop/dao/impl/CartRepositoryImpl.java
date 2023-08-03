package com.bookshop.dao.impl;

import com.bookshop.dao.CartRepository;
import com.bookshop.entity.Cart;
import com.bookshop.entity.User;
import com.bookshop.mapper.CartMapper;

import java.util.List;

public class CartRepositoryImpl extends AbstractDAO<Cart> implements CartRepository {

    @Override
    public Cart findByUser(User user) {
        StringBuilder sql = new StringBuilder("SELECT * FROM carts");
        sql.append(" WHERE user_id = " + user.getId());
        List<Cart> listCart =  query(sql.toString(), new CartMapper());
        return listCart.isEmpty()?null: listCart.get(0);
    }

    @Override
    public void update(Cart cart) {

    }

//    @Override
//    public Long save(Cart cart) {
//        StringBuilder sql = new StringBuilder("INSERT INTO carts(user_id)");
//        sql.append(" VALUES (?)");
//        return insert(sql.toString(), cart.getUser_id());
//    }
}
