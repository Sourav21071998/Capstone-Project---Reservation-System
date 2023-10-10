package com.training.reservationservice.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.reservationservice.entity.ReservationEntity;
import com.training.reservationservice.model.PaymentModel;
import com.training.reservationservice.model.ReservationModel;
import com.training.reservationservice.reservationRepository.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	RestTemplate restTemplate;

	@Transactional
	public Object createReservation(ReservationModel reservationModel) {
		log.info("Inside service of createReservation");
		Long customerId = reservationModel.getCustomerId();
		Boolean checkCustomer = restTemplate.getForObject("http://localhost:8081/customer/" + customerId,
				Boolean.class);
		if (checkCustomer == false)
			throw new RuntimeException("CustomerId:" + customerId + " is not present in database.");
		log.info("Customer validation done");
		Long hotelId = reservationModel.getHotelId();
		Boolean checkHotel = restTemplate.getForObject("http://localhost:8082/hotel/check/" + hotelId+"/"+customerId, Boolean.class);
		if (checkHotel == false)
			throw new RuntimeException("HotelId:" + hotelId + " is not available for booking");
		log.info("Hotel Room is available");
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setStartDate(LocalDate.parse(reservationModel.getStartDate()));
		reservationEntity.setEndDate(LocalDate.parse(reservationModel.getEndDate()));
		BeanUtils.copyProperties(reservationModel, reservationEntity);
		ReservationEntity save = reservationRepository.save(reservationEntity);
		PaymentModel paymentModel = new PaymentModel(customerId, reservationModel.getAmount(),hotelId);
		String msg = restTemplate.postForObject("http://localhost:8084/payment/create", paymentModel, String.class);
		log.info(msg);
		String notificationMessage = restTemplate.getForObject(
				"http://localhost:8083/notification/booking/confirmation/" + hotelId + "/" + customerId, String.class);
		return notificationMessage + " and bookingId is "+save.getId();
	}

	public String cancelResevation(Long id) {
		ReservationEntity entity = reservationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not present in database"));
		Long customerId = entity.getCustomerId();
		Long hotelId = entity.getHotelId();
		restTemplate.delete("http://localhost:8084/payment/delete/" + customerId+"/"+hotelId);
		log.info("Payment has been refunded");
		reservationRepository.delete(entity);
		Boolean value = restTemplate.getForObject("http://localhost:8082/hotel/" + customerId+"/"+hotelId,
				Boolean.class);
		String message="";
		if (value == true) {
			message = restTemplate.getForObject(
					"http://localhost:8083/notification/room/cancellation/" + hotelId + "/" + customerId, String.class);
		}
		return message;
	}

}
