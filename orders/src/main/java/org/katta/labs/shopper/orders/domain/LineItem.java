package org.katta.labs.shopper.orders.domain;

public class LineItem {
    private String name;
    private int quantity;

    public LineItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem lineItem = (LineItem) o;

        if (quantity != lineItem.quantity) return false;
        return name != null ? name.equals(lineItem.name) : lineItem.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
