package com.training.hotelmanagementservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.hotelmanagementservice.entity.HotelEntity;
import com.training.hotelmanagementservice.model.HotelModel;
import com.training.hotelmanagementservice.repository.HotelRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	public boolean checkAvailability(Long Id, Long customerId)
	{
		Optional<HotelEntity> hotel = hotelRepository.findById(Id);
		if (hotel == null || !hotel.get().isAvailable())
			return false;
		else
		{
			hotel.get().setCustomerId(customerId);
			hotel.get().setAvailable(false);
			createHotel(hotel.get());
			return true;
		}
	}
	
	
	public HotelEntity createHotel(HotelEntity hotelEntity)
	{
		System.out.println("Hotel availability: "+hotelEntity.isAvailable());
		HotelEntity save = hotelRepository.save(hotelEntity);
		return save;
	}
	
	public boolean cancelRoom(Long Id, Long hotelId)
	{
		HotelEntity entity = hotelRepository.findByCustomerIdAndId(Id,hotelId);
		log.info("HotelId: {} ",entity.getId());
		entity.setAvailable(true);
		entity.setCustomerId(null);
		hotelRepository.save(entity);
		return true;
	}
	
	public List<HotelModel> getAllHotels(Long customerId)
	{
		List<HotelEntity> entities = hotelRepository.findByCustomerId(customerId);
		List<HotelModel> models = new ArrayList<>();
		for(HotelEntity ent : entities)
		{
			HotelModel model = new HotelModel();
			BeanUtils.copyProperties(ent, model);
			models.add(model);
		}
		return models;
	}

}
