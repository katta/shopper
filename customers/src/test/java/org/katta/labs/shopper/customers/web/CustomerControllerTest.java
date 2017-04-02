package org.katta.labs.shopper.customers.web;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.customers.domain.Customer;
import org.katta.labs.shopper.customers.service.CustomerService;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mvc;
    private Customer customer;
    private String customerId;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Katta");
        customerId = "customer-id";
    }

    @Test
    public void shouldGetCustomer() throws Exception {
        when(customerService.get(customerId)).thenReturn(customer);

        MvcResult result = mvc.perform(get("/customers/{id}", customerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(JsonPath.read(response,"$.name").toString()).isEqualTo(customer.getName());
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        when(customerService.create(customer)).thenReturn(customerId);

        mvc.perform(post("/customers")
                .content(customer.toJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId").isString())
                .andExpect(status().isCreated());

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).create(captor.capture());

        Customer actual = captor.getValue();
        assertThat(actual.getName()).isEqualTo(customer.getName());
    }
}