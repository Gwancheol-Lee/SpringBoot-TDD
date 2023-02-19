package com.example.productorderservice.order;

import org.springframework.stereotype.Component;

import com.example.productorderservice.product.Product;

@Component
public class OrderService {
	private final OrderPort orderPort;
	
	OrderService(final OrderPort orderPort) {
		this.orderPort = orderPort;
	}
	
	public void createOrder(final CreateOrderRequest request) {
		final Product product = orderPort.getProductById(request.productId());
		
		final Order order = new Order(product, request.quantity());
		
		orderPort.save(order);
	}
}
