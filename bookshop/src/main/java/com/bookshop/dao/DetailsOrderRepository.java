package com.bookshop.dao;

import com.bookshop.entity.DetailsOrder;

public interface DetailsOrderRepository extends GenericDAO<DetailsOrder>{
    void save(DetailsOrder detailsOrder);
}
