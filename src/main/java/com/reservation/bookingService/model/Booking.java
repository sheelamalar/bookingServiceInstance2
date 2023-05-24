package com.reservation.bookingService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name="booking")
public class Booking {

	@Id
	@Column(name="booking_number")
	private int	bookingNumber;
	
	@Column(name="bus_number")
	private int busNumber;
	
	@Column(name="booking_date")
	private Date bookingDate;
	
	@Column(name="travel_date")
	private Date travelDate;
	
	@Column(name="source")
	private String source;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="seats")
	private int seats;
	
	@Column(name="status")
	private String status;
	
	
	public int getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(int bookingNumber) {
		System.out.println("setBookingNumber is called "+bookingNumber);
		
		this.bookingNumber = bookingNumber;
	}
	public int getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	
}
