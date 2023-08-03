package com.bookshop.service.impl;

import com.bookshop.dao.CartRepository;
import com.bookshop.entity.Cart;
import com.bookshop.entity.User;
import com.bookshop.service.CartService;

import javax.inject.Inject;

public class CartServiceImpl implements CartService {
    @Inject
    private CartRepository cartRepository;

    @Override
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void save(Cart cart) {
         cartRepository.update(cart);
    }
}
