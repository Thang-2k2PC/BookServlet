package com.bookshop.sort;

public class Sorter {
    private String sortPrice;

    private String Snxb;

    public Sorter(String sortPrice, String snxb) {
        this.sortPrice = sortPrice;
        Snxb = snxb;
    }

    public String getSortPrice() {
        return sortPrice;
    }

    public void setSortPrice(String sortPrice) {
        this.sortPrice = sortPrice;
    }



    public String getSnxb() {
        return Snxb;
    }

    public void setSnxb(String snxb) {
        Snxb = snxb;
    }
}
