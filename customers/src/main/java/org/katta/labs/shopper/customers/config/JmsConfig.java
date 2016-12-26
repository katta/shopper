package org.katta.labs.shopper.customers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {
    @Value("${activemq.broker.url}")
    private String brokerUrl;
    @Value("${activemq.queue.name}")
    private String queueName;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public String getQueueName() {
        return queueName;
    }
}
