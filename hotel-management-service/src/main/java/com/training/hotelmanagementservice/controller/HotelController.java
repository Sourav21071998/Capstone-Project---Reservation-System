package com.training.hotelmanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.hotelmanagementservice.entity.HotelEntity;
import com.training.hotelmanagementservice.model.HotelModel;
import com.training.hotelmanagementservice.service.HotelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotel")
@Slf4j
public class HotelController {

	@Autowired
	HotelService hotelService;

	@GetMapping("/check/{Id}/{customerId}")
	public boolean checkAvailability(@PathVariable Long Id,@PathVariable Long customerId) {
		boolean checkAvailability = hotelService.checkAvailability(Id,customerId);
		return checkAvailability;
	}

	@PostMapping("/create")
	public Object createHotel(@RequestBody HotelEntity hotelEntity) {
		HotelEntity createHotel = hotelService.createHotel(hotelEntity);
		return new ResponseEntity<>(createHotel, HttpStatus.CREATED);
	}

	@GetMapping("/{Id}/{hotelId}")
	public boolean cancelRoom(@PathVariable Long Id, @PathVariable Long hotelId) {
		log.info("Inside controller of cancel Room");
		log.info("customerId: {} "+Id);
		try {
			boolean isCancelled = hotelService.cancelRoom(Id,hotelId);
			return isCancelled;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return false;
		}

	}
	
	@GetMapping("/getAllHotels/{customerId}")
	public List<HotelModel> getAllHotels(@PathVariable Long customerId)
	{
		List<HotelModel> allHotels = hotelService.getAllHotels(customerId);
		return allHotels;
	}

}
