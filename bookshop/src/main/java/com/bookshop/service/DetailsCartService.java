package com.bookshop.service;

import com.bookshop.entity.DetailsCart;

import java.util.List;

public interface DetailsCartService {
    DetailsCart getDetailCartByProductAndCart(long product_id, long cart_id);

    void updateDetailsCart(DetailsCart detailsCart);

    List<DetailsCart> getDetailCartByCart(long id);

    void deleteDetailsCarts(DetailsCart detailsCart);

    void save(DetailsCart detailsCart);

    void deleteAllDetailsCarts(Long id);
}
