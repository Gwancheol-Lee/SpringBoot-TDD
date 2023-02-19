package com.example.productorderservice.payment;

interface PaymentGateway {
	void excute(Payment payment);
}
