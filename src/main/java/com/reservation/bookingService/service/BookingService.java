package com.reservation.bookingService.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.reservation.bookingService.model.Booking;

@Component
public interface BookingService {

	public Booking createBooking(@RequestBody Booking booking);

	public List<Booking> getAllBookings();

	public Booking getBooking(int bookingNumber);
	
	public Booking updateBooking(int bookingNumber, Booking booking);

	public Booking updateBooking(Booking booking);
}
