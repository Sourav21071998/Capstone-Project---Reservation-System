package com.training.hotelmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.hotelmanagementservice.entity.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
	
	HotelEntity findByCustomerIdAndId(Long Id, Long hotelId);
	
	List<HotelEntity> findByCustomerId(Long Id);

}
