package com.reservation.bookingService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.bookingService.model.Booking;
import com.reservation.bookingService.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	BookingService bookingService;
	
	/*
	 * CRUD - Create
	 * URL: http://localhost:8111/api/booking/book
	 */
	@PostMapping("/book")
	public Booking createBooking(@RequestBody Booking booking) {
		logger.debug("createBooking: "+booking.getBookingNumber());
		
		Booking bookedDetails = bookingService.createBooking(booking);		
		return bookedDetails;
	}
	
	/*
	 * CRUD - Receive
	 * URL: http://localhost:8111/api/booking/bookings
	 */
	@GetMapping("/bookings")
	public List<Booking> getAllBookings() {
		logger.debug("getAllBookings ");
		
		List<Booking> bookings = bookingService.getAllBookings();
		return bookings;
	}

	/*
	 * CRUD - Receive
	 * URL: http://localhost:8111/api/booking/booking/78
	 */
	@GetMapping("/booking/{bookingNumber}")
	public Booking getBookingById(@PathVariable(value = "bookingNumber") int bookingNumber) throws Exception {
		logger.debug("getBookingById: "+bookingNumber);
		
		Booking booking = bookingService.getBooking(bookingNumber);
		return booking;
	}

	/*
	 * CRUD - Update
	 * URL: http://localhost:8111/api/booking/booking/78
	 */
	@PutMapping("/booking/{bookingNumber}")
	public Booking updateBooking(@PathVariable(value = "bookingNumber") int bookingNumber, @RequestBody Booking booking) throws Exception {
		logger.debug("updateBooking: "+bookingNumber);
		
		Booking bookedDetails = bookingService.updateBooking(bookingNumber, booking);
		return bookedDetails;
	}

	/*
	 * CRUD - Delete
	 * URL: http://localhost:8111/api/booking/booking/89
	 */
	/*@DeleteMapping("/booking/{bookingNumber}")
	public ResponseEntity<?> deleteBooking(@PathVariable(value = "bookingNumber") int bookingNumber) throws Exception {
		logger.debug("deleteBooking: "+bookingNumber);
		
		Booking booking = bookingRepository.findByBookingNumber(bookingNumber);

		if(booking != null) {
			bookingRepository.delete(booking);
			return ResponseEntity.ok().build();
		}
	
		return null;
	}*/
}
