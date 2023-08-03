package com.bookshop.service.impl;

import com.bookshop.dao.DetailsOrderRepository;
import com.bookshop.entity.DetailsOrder;
import com.bookshop.service.DetailsOrderService;

import javax.inject.Inject;

public class DetailsOrderServiceImpl implements DetailsOrderService {

    @Inject
    private DetailsOrderRepository detailsOrderRepository;


    @Override
    public void save(DetailsOrder detailsOrder) {
        detailsOrderRepository.save(detailsOrder);
    }
}
