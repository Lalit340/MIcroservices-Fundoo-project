package com.bridgelabz.util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgelabz.user.model.Email;

@Component
public class Producer {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${exchange}")
	private String exchange;

	@Value("${routingkey}")
	private String routingkey;

	public void send(Email email) {
		rabbitTemplate.convertAndSend(exchange, routingkey, email);
	}
}
