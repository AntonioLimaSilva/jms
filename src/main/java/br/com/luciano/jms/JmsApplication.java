package br.com.luciano.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@SpringBootApplication
public class JmsApplication implements CommandLineRunner {

//	@Autowired
//	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(JmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String msg = "<pedido><name>Game</name></pedido>";
		this.jmsTemplate.convertAndSend(msg);
	}
}
