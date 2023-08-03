package com.bookshop.service;

import com.bookshop.entity.Cart;
import com.bookshop.entity.User;

public interface CartService {
    Cart getCartByUser(User user);

    void save(Cart cart);
}
