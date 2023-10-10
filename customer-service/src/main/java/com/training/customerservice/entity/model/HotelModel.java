package com.training.customerservice.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelModel {
	
	private Long id;
	
	private String name;
	
	private Long customerId;

	private boolean available;

}
