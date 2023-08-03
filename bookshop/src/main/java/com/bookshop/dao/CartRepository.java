package com.bookshop.dao;

import com.bookshop.entity.Cart;
import com.bookshop.entity.User;

public interface CartRepository extends GenericDAO<Cart>{
    Cart findByUser(User user);

    void update(Cart cart);
}
