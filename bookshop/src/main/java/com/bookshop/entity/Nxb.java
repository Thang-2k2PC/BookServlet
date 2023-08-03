package com.bookshop.entity;

import java.util.List;


public class Nxb {

    private Long id;

    private String nameNxb;

    private List<Product> listProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameNxb() {
        return nameNxb;
    }

    public void setNameNxb(String nameNxb) {
        this.nameNxb = nameNxb;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
