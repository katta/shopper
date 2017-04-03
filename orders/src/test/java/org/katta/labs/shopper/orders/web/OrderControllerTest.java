package org.katta.labs.shopper.orders.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.orders.domain.LineItem;
import org.katta.labs.shopper.orders.domain.Order;
import org.katta.labs.shopper.orders.service.OrderService;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mvc;

    @Value(value = "classpath:order.json")
    private Resource orderJson;

    @Test
    public void shouldCreateOrder() throws Exception {
        String orderRequest = StreamUtils.copyToString(orderJson.getInputStream(), Charset.defaultCharset());

        mvc.perform(post("/orders")
                .content(orderRequest)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orderService).create(captor.capture());

        Order order = captor.getValue();
        assertThat(order.getCustomerId()).isEqualTo("customer-id");
        assertThat(order.lineItemCount()).isEqualTo(2);
        assertThat(order.hasLineItem(new LineItem("item1", 1))).isTrue();
        assertThat(order.hasLineItem(new LineItem("item2", 2))).isTrue();
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

