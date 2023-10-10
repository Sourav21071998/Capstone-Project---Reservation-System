package com.training.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public String notificationMessage(String name) {
		String message = "Hi, Welcome, " + name;
		return message;
	}

	public String notificationBookingConfirmation(Long hotelId, Long customerId) {
		String message = "Booking has been confirmed for hotelId: " + hotelId + " and customerId: " + customerId
				+ ". Payment is successful";
		return message;
	}

	public String roomCancellation(Long hotelId, Long customerId) {
		String message = "Booking has been cancelled for hotelId: " + hotelId + " and customerId: " + customerId
				+ ". Payment has been refunded";
		return message;
	}
}
