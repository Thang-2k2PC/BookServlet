package com.bookshop.service.impl;

import com.bookshop.dao.ProductRepository;
import com.bookshop.dto.ProductDTO;
import com.bookshop.entity.Product;
import com.bookshop.paging.Pageble;
import com.bookshop.service.ProductService;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Override
    public List<Product> getLatestProduct() {
        return productRepository.findFirst12ByCategoryNameCategoryContainingIgnoreCaseOrderByIdDesc("Tieng Anh");
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getAllProductPageByCategoryId(Pageble pageble) {
        return productRepository.findAllProductPageByCategoryId(pageble);
    }


    @Override
    public List<Product> getProductAndNxbByCategoryId(long category_id) {
        return productRepository.findProductAndNxbByCategoryId(category_id);
    }

//    @Override
//    public int getTotalItemProductByNxb(long nxb_id) {
//        return productRepository.getTotalItemProductByNxb(nxb_id);
//    }

    @Override
    public int getTotalItemByPageble(Pageble pageble) {
        return productRepository.getTotalItemByPageble(pageble);
    }

    @Override
    public List<Product> getAllProductByIdList(Set<Long> idList) {
        return productRepository.findByIdIn(idList);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByFilter(Pageble pageble) {
        return productRepository.findAllProductByFilter(pageble);
    }

    @Override
    public int getTotalItemByFilter(Pageble pageble) {
        return productRepository.getTotalItemByFilter(pageble);
    }

    @Override
    public void saveNew(ProductDTO product) {
         productRepository.saveNew(product);
    }

    @Override
    public Product getProductByNameProduct(String nameProduct) {
        return productRepository.findProductByNameProduct(nameProduct);
    }

    @Override
    public void saveUpdate(ProductDTO productDTO) {
        productRepository.saveUpdate(productDTO);
    }

    @Override
    public void delete(String product_id) {
        productRepository.delete(product_id);
    }

    @Override
    public Product getById(long product_id) {
        return productRepository.findById(product_id);
    }
}
