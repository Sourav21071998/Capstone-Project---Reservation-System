package com.training.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	Payment findByCustomerIdAndHotelId(Long Id, Long hotelId);

}
