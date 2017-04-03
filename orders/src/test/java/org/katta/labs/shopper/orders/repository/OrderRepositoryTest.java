package org.katta.labs.shopper.orders.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.orders.domain.CustomerView;
import org.katta.labs.shopper.orders.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldSaveOrder() throws Exception {
        String customerId = "customer-id";
        CustomerView customer = new CustomerView(customerId, "Katta");

        Order order = new Order(customerId);
        order.attachCustomer(customer);

        Order savedOrder = orderRepository.save(order);

        Order actualOrder = orderRepository.findOne(savedOrder.getId());
        assertThat(actualOrder.getCustomerView().getName()).isEqualTo(customer.getName());
        assertThat(actualOrder.getCustomerView().getId()).isEqualTo(customer.getId());
    }

    @TestConfiguration
    static class JmsTestConfig {
        @Bean
        @Primary
        public JmsListenerContainerFactory containerFactory() {
            return new DefaultJmsListenerContainerFactory();
        }
    }
}