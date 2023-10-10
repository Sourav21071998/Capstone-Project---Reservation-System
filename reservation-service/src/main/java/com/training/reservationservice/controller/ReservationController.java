package com.training.reservationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.reservationservice.entity.ReservationEntity;
import com.training.reservationservice.model.ReservationModel;
import com.training.reservationservice.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@PostMapping("/create")
	public ResponseEntity<?> createReservation(@RequestBody ReservationModel reservationModel) {
		try {
			log.info("Inside controller of createReservation");
			String createReservation = (String) reservationService.createReservation(reservationModel);
			return new ResponseEntity<>(createReservation, HttpStatus.CREATED);
		} catch (Exception ex) {
			String message = ex.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<?> cancelReservation(@PathVariable Long bookingId) {
		try {
			String cancelResevation = reservationService.cancelResevation(bookingId);
			return new ResponseEntity<>(cancelResevation, HttpStatus.OK);
		} catch (Exception ex) {
			String message = ex.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
