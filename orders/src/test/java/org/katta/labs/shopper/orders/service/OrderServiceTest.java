package org.katta.labs.shopper.orders.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.orders.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @MockBean
    private CustomerRepository customerRepository;

    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void shouldConstructOrderWithCustomerView() throws Exception {

    }
}