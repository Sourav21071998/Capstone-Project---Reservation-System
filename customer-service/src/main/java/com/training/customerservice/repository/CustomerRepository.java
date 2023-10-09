package com.training.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.customerservice.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

}
