package org.katta.labs.shopper.orders.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderTest {

    private String customerId;

    @Before
    public void setUp() throws Exception {
        customerId = "customer-id";
    }

    @Test
    public void shouldAddLineItem() throws Exception {
        Order order = new Order(customerId);
        LineItem item = new LineItem("item1", 2);
        order.addLineItem(item);

        assertThat(order.lineItemCount()).isEqualTo(1);
        assertThat(order.hasLineItem(item)).isTrue();
    }

    @Test
    public void shouldNotAddDuplicateLineItem() throws Exception {
        Order order = new Order(customerId);
        LineItem item1 = new LineItem("item1", 2);
        LineItem item2 = new LineItem("item1", 2);
        order.addLineItem(item1);
        order.addLineItem(item2);

        assertThat(order.lineItemCount()).isEqualTo(1);
        assertThat(order.hasLineItem(item1)).isTrue();
    }

    @Test
    public void shouldAddMultipleLineItems() throws Exception {
        Order order = new Order(customerId);
        LineItem item1 = new LineItem("item1", 2);
        LineItem item2 = new LineItem("item2", 1);
        order.addLineItem(item1);
        order.addLineItem(item2);

        assertThat(order.lineItemCount()).isEqualTo(2);
        assertThat(order.hasLineItem(item1)).isTrue();
        assertThat(order.hasLineItem(item2)).isTrue();
    }

    @Test
    public void shouldCreateOrderDateAsCurrentDate() throws Exception {
        Order order = new Order(customerId);
        LineItem item1 = new LineItem("item1", 2);
        order.addLineItem(item1);

        DateTime createdDate = order.getCreatedDate();
        DateTimeComparator comparator = DateTimeComparator.getInstance(
                DateTimeFieldType.minuteOfHour());
        assertThat(comparator.compare(createdDate, DateTime.now())).isEqualTo(0);
    }
}