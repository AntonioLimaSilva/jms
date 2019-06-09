package br.com.luciano.jms.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListeners({
            @JmsListener(destination = "queue.dev"),
            @JmsListener(destination = "queue.prod")
    })
    public void reveiver(String msg) {
        System.out.println("Received Message : " + msg);
    }

}
