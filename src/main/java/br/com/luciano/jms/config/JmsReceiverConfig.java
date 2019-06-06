package br.com.luciano.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.Queue;

@Configuration
@EnableJms
public class JmsReceiverConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

//    @Bean
//    public Queue queue() {
//        return new ActiveMQQueue("simple-jms-queue");
//    }

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(this.brokerUrl);

        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(receiverActiveMQConnectionFactory());
        jmsListenerContainerFactory.setConcurrency("3-10");
        return jmsListenerContainerFactory;
    }

}
