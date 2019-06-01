package br.com.luciano.jms.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
public class ProducerResource {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("publish/{msg}")
    public String publish(@PathVariable String msg) {
        this.jmsTemplate.convertAndSend(queue, msg);
        return "Your message <b>" + msg + "</br> published successfully!";
    }

}
