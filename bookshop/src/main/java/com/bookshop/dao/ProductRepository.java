package com.bookshop.dao;

import com.bookshop.dto.ProductDTO;
import com.bookshop.entity.Product;
import com.bookshop.paging.Pageble;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends GenericDAO<Product> {

    List<Product> findFirst12ByCategoryNameCategoryContainingIgnoreCaseOrderByIdDesc(String tieng_anh);

    Product findProductById(long id);

    List<Product> findAllProductPageByCategoryId(Pageble pageble);

//    int getTotalItem(long category_id);

    List<Product> findProductAndNxbByCategoryId(long category_id);

    int getTotalItemByPageble(Pageble pageble);

    List<Product> findByIdIn(Set<Long> idList);

    List<Product> findAll();

    List<Product> findAllProductByFilter(Pageble pageble);

    int getTotalItemByFilter(Pageble pageble);

    void saveNew(ProductDTO productDTO);

    Product findProductByNameProduct(String nameProduct);

    void saveUpdate(ProductDTO productDTO);

    void delete(String product_id);

    Product findById(long id);
}
