package com.example.productorderservice.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.productorderservice.order.application.service.CreateOrderRequest;
import com.example.productorderservice.order.application.service.OrderService;
import com.example.productorderservice.product.ProductSteps;
import com.example.productorderservice.product.application.service.ProductService;

class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	@Test
	void 상품주문() {
		productService.addProduct(ProductSteps.상품등록요청_생성());
		final CreateOrderRequest request = 상품주문요청_생성();
		
		orderService.createOrder(request);
	}
	
	private static CreateOrderRequest 상품주문요청_생성() {
		final Long productId = 1L;
		final int quantity = 2;
		return new CreateOrderRequest(productId, quantity);
	}
}
