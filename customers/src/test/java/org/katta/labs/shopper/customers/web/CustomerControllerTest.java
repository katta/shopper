package org.katta.labs.shopper.customers.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.customers.domain.Customer;
import org.katta.labs.shopper.customers.service.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customers resource"));
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        Customer customer = new Customer("Katta");

        mvc.perform(MockMvcRequestBuilders.post("/customers")
                    .content(customer.toJson())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}