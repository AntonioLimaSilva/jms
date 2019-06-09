package br.com.luciano.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.Queue;

@Configuration
public class JmsSenderConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${activemp.queue-prod}")
    private String queueDev;

    @Value("${activemp.queue-dev}")
    private String queueProd;

    @Bean
    public ActiveMQConnectionFactory senderConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);

        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(senderConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);

        return cachingConnectionFactory;
    }

    @Bean
    public Destination destination() {
        return new ActiveMQQueue(queueDev);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueProd);
    }

    @Bean
    public JmsTemplate queueJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
        jmsTemplate.setDefaultDestination(destination());
        jmsTemplate.setReceiveTimeout(5000);

        return jmsTemplate;
    }

}
