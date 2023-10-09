package com.training.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.paymentservice.model.PaymentModel;
import com.training.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/create")
	public Object makePayment(@RequestBody PaymentModel paymentModel)
	{
		PaymentModel processPayment = paymentService.processPayment(paymentModel);
		String msg = "Payment successful";
		return msg;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> cancelPayment(@PathVariable Long id)
	{
		paymentService.refund(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
