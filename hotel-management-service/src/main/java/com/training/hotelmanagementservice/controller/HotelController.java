package com.training.hotelmanagementservice.controller;

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
import com.training.hotelmanagementservice.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping("/check/{Id}")
	public boolean checkAvailability(@PathVariable Long Id)
	{
		boolean checkAvailability = hotelService.checkAvailability(Id);
		return checkAvailability;
	}
	
	@PostMapping("/create")
	public Object createHotel(@RequestBody HotelEntity hotelEntity)
	{
		HotelEntity createHotel = hotelService.createHotel(hotelEntity);
		return new ResponseEntity<>(createHotel, HttpStatus.CREATED);
	}

}
