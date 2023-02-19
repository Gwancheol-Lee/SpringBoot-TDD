package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;

class PaymentAdapter implements PaymentPort {
	
	private final PaymentGateway paymentGateway;
	private final PaymentRepository paymentRepository;
	
	PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
		this.paymentGateway = paymentGateway;
		this.paymentRepository = paymentRepository;
	}
	
	@Override
	public Order getOrder(final Long orderId) {
		return new Order(new Product("상품1", 1000, DiscountPolicy.NONE), 2);
	}

	@Override
	public void pay(final Payment payment) {
		paymentGateway.excute(payment);
	}

	@Override
	public void save(final Payment payment) {
		paymentRepository.save(payment);
		
	}
}
