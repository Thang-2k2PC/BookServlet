package com.bookshop.entity;

public class Product {

    private Long id;


    private String nameProduct;

    private String infoProduct;

    private long price;

    private int wareHouse;

    private int unitSelling;


    private Nxb nxb = new Nxb();
    private Category category = new Category();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(int wareHouse) {
        this.wareHouse = wareHouse;
    }

    public int getUnitSelling() {
        return unitSelling;
    }

    public void setUnitSelling(int unitSelling) {
        this.unitSelling = unitSelling;
    }

    public Nxb getNxb() {
        return nxb;
    }

    public void setNxb(Nxb nxb) {
        this.nxb = nxb;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
