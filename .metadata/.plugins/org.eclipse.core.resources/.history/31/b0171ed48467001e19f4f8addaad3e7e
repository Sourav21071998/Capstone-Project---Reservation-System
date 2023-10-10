package com.training.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.customerservice.entity.CustomerEntity;
import com.training.customerservice.entity.model.CustomerModel;
import com.training.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public CustomerModel registerCustomer(CustomerModel model) {
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(model, customerEntity);
		CustomerEntity save = customerRepository.save(customerEntity);
		CustomerModel customerModel = new CustomerModel();
		BeanUtils.copyProperties(save, customerModel);
		return customerModel;
	}

	public CustomerModel updateCustomer(Long Id, CustomerModel model) {
		CustomerModel saveModel = null;
		Optional<CustomerEntity> previousEnity = customerRepository.findById(Id);
		if (previousEnity == null) {
			throw new RuntimeException("Id is not present in database");
		} else {
			CustomerEntity customerEntity = previousEnity.get();
			customerEntity.setEmail(model.getEmail());
			customerEntity.setName(model.getName());
			customerEntity.setPassword(model.getPassword());
			CustomerEntity save = customerRepository.save(customerEntity);
			saveModel = new CustomerModel();
			BeanUtils.copyProperties(save, saveModel);
		}
		return saveModel;
	}

	public List<Object> viewHotels() {
		return null;
	}

	public boolean checkCustomer(Long id) {
		Optional<CustomerEntity> check = customerRepository.findById(id);
		if (check.isEmpty())
			return false;
		else
			return true;

	}

}
