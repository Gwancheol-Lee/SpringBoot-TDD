package com.example.productorderservice.payment.adapter;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productorderservice.payment.domain.Payment;


interface PaymentRepository extends JpaRepository<Payment, Long>{
	
}
