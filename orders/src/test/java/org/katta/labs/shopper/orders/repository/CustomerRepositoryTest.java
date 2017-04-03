package org.katta.labs.shopper.orders.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.orders.domain.CustomerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;


import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CustomerRepository customerRepository;

    @Value(value = "classpath:customer.json")
    private Resource customerViewJson;

    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void shouldConstructOrderWithCustomerView() throws Exception {
        String customer = StreamUtils.copyToString(customerViewJson.getInputStream(), Charset.defaultCharset());

        server.expect(once(), requestTo("http://localhost:8081/customers/customer-id"))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(customer, MediaType.APPLICATION_JSON));

        CustomerView customerView = customerRepository.findCustomer("customer-id");
        assertThat(customerView.getName()).isEqualTo("Srivatsa Katta");

        server.verify();
    }

}