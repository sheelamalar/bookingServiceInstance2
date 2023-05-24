package com.reservation.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reservation.bookingService.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query(value = "select * from booking where booking_number = ?1",  nativeQuery = true)
	Booking findByBookingNumber(int bookingNumber);

}
