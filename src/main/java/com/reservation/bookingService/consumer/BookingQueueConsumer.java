package com.reservation.bookingService.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.reservation.bookingService.constants.BookingConstants;
import com.reservation.bookingService.model.Booking;
import com.reservation.bookingService.service.BookingService;

@Component
public class BookingQueueConsumer {
	Logger logger = LoggerFactory.getLogger(BookingQueueConsumer.class);

	@Autowired
	BookingService bookingService;

	@RabbitListener(queues="bookingQueue",ackMode="MANUAL")
	public void receiveMessageFromBookingQueue(String message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
		logger.debug("receiveMessageFromBookingQueue: "+message);
		
		try {
			int bookingNumber = Integer.parseInt(message);
			
			Booking booking = bookingService.getBooking(bookingNumber);
			booking.setStatus(BookingConstants.BOOKING_CONFIRMED);
		
			Booking updatedBooking = bookingService.updateBooking(booking);
			if(updatedBooking != null) {
				logger.debug("Queue processed: Updated Booking");
				channel.basicAck(tag, false);
			}
			
		} catch(Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
	}
}
