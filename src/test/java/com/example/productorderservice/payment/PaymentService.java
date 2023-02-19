package com.example.productorderservice.payment;

import org.springframework.stereotype.Component;

import com.example.productorderservice.order.Order;

@Component
public class PaymentService {
	
	private final PaymentPort paymentPort;

	PaymentService(PaymentPort paymentPort) {
		this.paymentPort = paymentPort;
	}
	
	public void payment(PaymentRequest request) {
		Order order = paymentPort.getOrder(request.orderId());
		
		final Payment payment = new Payment(order, request.cardNumber());
		
		paymentPort.pay(payment.getPrice(), payment.getCardNumber());
		paymentPort.save(payment);
	}
}