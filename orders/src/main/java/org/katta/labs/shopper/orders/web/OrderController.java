package org.katta.labs.shopper.orders.web;

import org.katta.labs.shopper.orders.domain.LineItem;
import org.katta.labs.shopper.orders.domain.Order;
import org.katta.labs.shopper.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createOrder(@RequestBody Map orderRequest) {
        Order order = buildOrderFromRequest(orderRequest);
        String orderId = orderService.create(order);
        return Collections.singletonMap("orderId", orderId);
    }

    private Order buildOrderFromRequest(@RequestBody Map orderRequest) {
        Order order = new Order((String) orderRequest.get("customerId"));
        List<Map> items = (List) orderRequest.get("items");
        items.forEach(item -> order.addLineItem(new LineItem(((String) item.get("name")), ((Integer) item.get("quantity")))));
        return order;
    }
}
