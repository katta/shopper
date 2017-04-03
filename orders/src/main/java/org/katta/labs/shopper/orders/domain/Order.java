package org.katta.labs.shopper.orders.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

public class Order {
    @Id
    private String id;
    private String customerId;
    private DateTime createdDate;
    private Set<LineItem> lineItems;
    private CustomerView customerView;

    public Order(String customerId) {
        this.customerId = customerId;

        createdDate = DateTime.now();
        lineItems = new HashSet<>();
    }


    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public int lineItemCount() {
        return lineItems.size();
    }

    public boolean hasLineItem(LineItem item) {
        return lineItems.contains(item);
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void attachCustomer(CustomerView customerView) {
        this.customerView = customerView;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }
}
