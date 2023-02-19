package com.example.productorderservice.payment.adapter;

import org.springframework.stereotype.Component;

import com.example.productorderservice.order.adapter.OrderRepository;
import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.payment.application.port.PaymentPort;
import com.example.productorderservice.payment.domain.Payment;

@Component
public class PaymentAdapter implements PaymentPort {
	
	private final PaymentGateway paymentGateway;
	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	
	PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository, OrderRepository orderRepository) {
		this.paymentGateway = paymentGateway;
		this.paymentRepository = paymentRepository;
		this.orderRepository = orderRepository;
	}
	
	@Override
	public Order getOrder(final Long orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
	}

	@Override
	public void pay(final int totalPrice, final String cardNumber) {
		paymentGateway.excute(totalPrice, cardNumber);
	}

	@Override
	public void save(final Payment payment) {
		paymentRepository.save(payment);
		
	}
}
