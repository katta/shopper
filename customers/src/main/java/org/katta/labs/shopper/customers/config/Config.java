package org.katta.labs.shopper.customers.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
public class Config {

    @Autowired
    private JmsConfig jmsConfig;

    @Bean
    public JmsTemplate jmsTemplate() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(jmsConfig.getBrokerUrl());

        JmsTemplate jmsTemplate = new JmsTemplate(new CachingConnectionFactory(activeMQConnectionFactory));
        jmsTemplate.setMessageConverter(new MappingJackson2MessageConverter());
        
        return jmsTemplate;
    }

}
