package com.reservation.bookingService.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.reservation.bookingService.constants.BookingConstants;
import com.reservation.bookingService.model.Booking;
import com.reservation.bookingService.repository.BookingRepository;
import com.reservation.bookingService.service.BookingService;

@Component
public class BookingServiceImpl implements BookingService {

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
	
	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AmqpTemplate amqpTemplate;	
	
	public Booking createBooking(Booking booking) {
		//Direct REST call
		logger.debug("Checking bus availability for: "+booking.getBusNumber());
		ResponseEntity<Integer> responseEntity = restTemplate
                .getForEntity("http://localhost:8117/api/inventory/route/" + booking.getBusNumber(),
                		Integer.class);
		
		int availableSeats = responseEntity.getBody();
		logger.debug("Available Seats: "+availableSeats);
		
		if(availableSeats >=  booking.getSeats()) {
			logger.debug("Booking In-progress");
			
			booking.setStatus(BookingConstants.BOOKING_PENDING);
			booking.setBookingDate(new Date());
			
			Booking bookingResult = bookingRepository.save(booking);
			
			if(bookingResult != null) {
				//Register payment queue
				String exchange = "paymentDirectExchange";
				String routingKey = "queue.paymentQueue";
				int message = booking.getBookingNumber();
				
				amqpTemplate.convertAndSend(exchange, routingKey, message);
				logger.debug("Registered Inventory queue using direct exchange");
			}
		}
		
		return null;
	}
	
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}
	
	public Booking getBooking(int bookingNumber)  {
		return bookingRepository.findByBookingNumber(bookingNumber);
	}
	
	public Booking updateBooking(int bookingNumber, Booking booking) {
		Booking bookingFromDB = bookingRepository.findByBookingNumber(bookingNumber);

		if(bookingFromDB != null) {
			bookingFromDB.setBookingNumber(booking.getBookingNumber());
			bookingFromDB.setBusNumber(booking.getBusNumber());
			bookingFromDB.setDestination(booking.getDestination());
			bookingFromDB.setSeats(booking.getSeats());
			bookingFromDB.setSource(booking.getSource());
			bookingFromDB.setStatus(booking.getStatus());
	
			updateBooking(bookingFromDB);
		}
		
		return null;
	}
	
	public Booking updateBooking(Booking booking) {
		Booking updatedBooking = bookingRepository.save(booking);
		return updatedBooking;
	}
}
