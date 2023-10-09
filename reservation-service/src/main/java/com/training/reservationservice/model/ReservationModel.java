package com.training.reservationservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationModel {
	
	private Long id;
	
	private Long customerId;
	
	private Long hotelId;
	
	private String startDate;
	
	private String endDate;
	
    private BigDecimal amount;

}
