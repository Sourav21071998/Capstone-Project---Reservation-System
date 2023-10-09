package com.training.hotelmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.hotelmanagementservice.entity.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long>{

}
