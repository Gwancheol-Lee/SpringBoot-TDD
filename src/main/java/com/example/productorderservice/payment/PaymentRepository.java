package com.example.productorderservice.payment;

import org.springframework.data.jpa.repository.JpaRepository;


interface PaymentRepository extends JpaRepository<Payment, Long>{
	
}
