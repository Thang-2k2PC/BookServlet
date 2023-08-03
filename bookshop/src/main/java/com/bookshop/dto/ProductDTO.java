package com.bookshop.dto;


import javax.servlet.http.Part;

public class ProductDTO {
    private long id;
    private String nameProduct;
    private String infoProduct;
    private int wareHouse;
    private int price;
    private long nxb_id;

    private long category_id ;
    private Part images;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getInfoProduct() {
        return infoProduct;
    }

    public void setInfoProduct(String infoProduct) {
        this.infoProduct = infoProduct;
    }

    public int getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(int wareHouse) {
        this.wareHouse = wareHouse;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getNxb_id() {
        return nxb_id;
    }

    public void setNxb_id(long nxb_id) {
        this.nxb_id = nxb_id;
    }

    public Part getImages() {
        return images;
    }

    public void setImages(Part images) {
        this.images = images;
    }
}
