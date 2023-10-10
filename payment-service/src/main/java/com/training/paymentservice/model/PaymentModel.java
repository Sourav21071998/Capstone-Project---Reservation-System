package com.training.paymentservice.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
	
    private Long id;
    
    private Long customerId;
    
    private BigDecimal amount;

    private Long hotelId;
    
}
