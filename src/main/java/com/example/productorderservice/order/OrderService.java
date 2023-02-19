package com.example.productorderservice.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productorderservice.product.Product;

@RestController
@RequestMapping("/orders")
public class OrderService {
	private final OrderPort orderPort;
	
	OrderService(final OrderPort orderPort) {
		this.orderPort = orderPort;
	}
	
	@PostMapping
	public ResponseEntity<Void> createOrder(
			@RequestBody final CreateOrderRequest request) {
		final Product product = orderPort.getProductById(request.productId());
		
		final Order order = new Order(product, request.quantity());
		
		orderPort.save(order);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
