package com.bookshop.entity;


import java.sql.Timestamp;
import java.util.List;


public class Order {

    private Long id;

    private String addressReceive;

    private String note;

    private String nameCustomer;


    private Timestamp dateOrder;


    private Timestamp dateDelivery;


    private Timestamp dateReceive;

    private String phone;


    private String statusOrder;


    private long user_id_order;

    private long shipper_id;
    private String shipper_name;

    private long quantity_order;


    private long sumPrice;
    private long price;

    private long product_id;
    private Product product = new Product();
    private User user = new User();

    public long getUser_id_order() {
        return user_id_order;
    }

    public void setUser_id_order(long user_id_order) {
        this.user_id_order = user_id_order;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity_order() {
        return quantity_order;
    }

    public void setQuantity_order(long quantity_order) {
        this.quantity_order = quantity_order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAddressReceive() {
        return addressReceive;
    }

    public void setAddressReceive(String addressReceive) {
        this.addressReceive = addressReceive;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Timestamp getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Timestamp dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Timestamp getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(Timestamp dateReceive) {
        this.dateReceive = dateReceive;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }


    public long getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(long shipper_id) {
        this.shipper_id = shipper_id;
    }

    public long getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(long sumPrice) {
        this.sumPrice = sumPrice;
    }
}
