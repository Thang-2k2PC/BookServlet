package com.bookshop.dao;

import com.bookshop.entity.DetailsCart;

import java.util.List;

public interface DetailsCartsRepository extends GenericDAO<DetailsCart>{
    DetailsCart getDetailCartByProductAndCart(long product_id, long cart_id);

    void update(DetailsCart detailsCart);

    List<DetailsCart> findDetailsCartByCart(long id);

    void delete(DetailsCart detailsCart);

    void updateDetailCart(DetailsCart detailsCart);

    void deleteAllDetailsCarts(Long id);
}
