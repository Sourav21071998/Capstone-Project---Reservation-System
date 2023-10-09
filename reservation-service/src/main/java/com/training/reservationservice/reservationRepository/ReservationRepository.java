package com.training.reservationservice.reservationRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.reservationservice.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{

}
