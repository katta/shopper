package org.katta.labs.shopper.customers.web;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetCustomer() throws Exception {
        mvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customers resource"));
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        Customer customer = new Customer("Katta");
        String customerId = "customer-id";
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