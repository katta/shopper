package org.katta.labs.shopper.orders.repository;

import org.katta.labs.shopper.orders.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
