package com.bookshop.service.impl;

import com.bookshop.dao.DetailsCartsRepository;
import com.bookshop.entity.DetailsCart;
import com.bookshop.service.DetailsCartService;

import javax.inject.Inject;
import java.util.List;

public class DetailsCartServiceImpl implements DetailsCartService {

    @Inject
    private DetailsCartsRepository detailsCartsRepository;
    @Override
    public DetailsCart getDetailCartByProductAndCart(long product_id, long cart_id) {
        return detailsCartsRepository.getDetailCartByProductAndCart(product_id, cart_id);
    }

    @Override
    public void updateDetailsCart(DetailsCart detailsCart) {
         detailsCartsRepository.update(detailsCart);
    }

    @Override
    public List<DetailsCart> getDetailCartByCart(long id) {
        return detailsCartsRepository.findDetailsCartByCart(id);
    }

    @Override
    public void deleteDetailsCarts(DetailsCart detailsCart) {
        detailsCartsRepository.delete(detailsCart);
    }

    @Override
    public void save(DetailsCart detailsCart) {
        detailsCartsRepository.updateDetailCart(detailsCart);
    }

    @Override
    public void deleteAllDetailsCarts(Long id) {
        detailsCartsRepository.deleteAllDetailsCarts(id);
    }
}
