package com.bookshop.dto;

public class SearchOrderDTO {
    private String statusOrder;
    private String fromDate;
    private String toDate;

    public SearchOrderDTO() {
        statusOrder = "";
        fromDate = "";
        toDate = "";
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
