package org.katta.labs.shopper.customers.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.katta.labs.shopper.customers.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void shouldCreateNewCustomer() throws Exception {
        String name = "Srivatsa";
        repository.save(new Customer(name));

        Customer actual = repository.findByName(name);
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getId()).isNotEmpty();
    }
}