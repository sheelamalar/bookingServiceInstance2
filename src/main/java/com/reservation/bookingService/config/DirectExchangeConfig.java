package com.reservation.bookingService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;

@Configuration
public class DirectExchangeConfig {
	Logger logger = LoggerFactory.getLogger(DirectExchangeConfig.class);
	
	@Bean
	Queue paymentQueue() {
		logger.debug("paymentQueue");
		
		//false: non-durable
		return new Queue("paymentQueue", false);		
	}
	
	@Bean
	DirectExchange paymentDirectExchange() {
		logger.debug("paymentDirectExchange");
		
		return new DirectExchange("paymentDirectExchange");
	}
	
	@Bean
	Binding paymentDirectBinding(Queue paymentQueue, DirectExchange paymentDirectExchange) {
		logger.debug("paymentDirectBinding");
		
		return BindingBuilder.bind(paymentQueue).to(paymentDirectExchange).with("queue.paymentQueue");
	}
	

}
