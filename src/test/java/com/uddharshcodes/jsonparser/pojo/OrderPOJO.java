package com.uddharshcodes.jsonparser.pojo;

import java.util.List;

public class OrderPOJO {
    private String orderId;
    private CustomerPOJO customer;
    private List<ItemPojo> items;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerPOJO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerPOJO customer) {
        this.customer = customer;
    }

    public List<ItemPojo> getItems() {
        return items;
    }

    public void setItems(List<ItemPojo> items) {
        this.items = items;
    }
}
