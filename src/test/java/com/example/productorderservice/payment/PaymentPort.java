package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;

interface PaymentPort {
	Order getOrder(Long orderId);
	void pay(Payment payment);
	void save(Payment payment);
}
