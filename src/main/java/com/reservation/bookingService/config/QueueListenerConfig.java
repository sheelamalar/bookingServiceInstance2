package com.reservation.bookingService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.context.annotation.Bean;

public class QueueListenerConfig {
	Logger logger = LoggerFactory.getLogger(QueueListenerConfig.class);

	@Bean("rabbitListnerContainerFactory")
	public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		logger.debug("I am listening ..........");
		
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);

		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		return factory;
	}
	
}
