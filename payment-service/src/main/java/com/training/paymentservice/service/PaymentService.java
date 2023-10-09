package com.training.paymentservice.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.paymentservice.entity.Payment;
import com.training.paymentservice.model.PaymentModel;
import com.training.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	public PaymentModel processPayment(PaymentModel paymentModel) {
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentModel, payment);
		Payment save = paymentRepository.save(payment);
		PaymentModel payModel = new PaymentModel();
		BeanUtils.copyProperties(save, payModel);
		return payModel;
	}

	public void refund(Long Id) {
		Payment payment = paymentRepository.findById(Id)
				.orElseThrow(() -> new RuntimeException("Id not present in database"));
		paymentRepository.delete(payment);
	}

}
