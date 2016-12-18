package org.katta.labs.shopper.customers.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void shouldCreateNewCustomer() {
        Customer customer = new Customer("Srivatsa");
        assertThat(customer.getName(), is("Srivatsa"));
    }

}