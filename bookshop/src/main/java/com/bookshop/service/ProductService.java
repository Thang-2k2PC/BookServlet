package com.bookshop.service;

import com.bookshop.dto.ProductDTO;
import com.bookshop.entity.Product;
import com.bookshop.paging.Pageble;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> getLatestProduct();

    Product getProductById(long id);

    List<Product> getAllProductPageByCategoryId(Pageble pageble);

//    int getTotalItem(long category_id);

//    Iterable<Product> getProductAndNxbByCategoryId(long category_id);
    List<Product> getProductAndNxbByCategoryId(long category_id);

//    int getTotalItemProductByNxb(long nxb_id);

    int getTotalItemByPageble(Pageble pageble);

    List<Product> getAllProductByIdList(Set<Long> idList);

    List<Product> getAllProduct();

    List<Product> getAllProductByFilter(Pageble pageble);

    int getTotalItemByFilter(Pageble pageble);

    void saveNew(ProductDTO productDTO);

    Product getProductByNameProduct(String nameProduct);

    void saveUpdate(ProductDTO productDTO);

    void delete(String product_id);

    Product getById(long product_id);
}
