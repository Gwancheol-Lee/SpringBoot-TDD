package com.example.productorderservice.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productorderservice.order.OrderService;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.product.ProductService;
import com.example.productorderservice.product.ProductSteps;

@SpringBootTest
class PaymentServiceTest {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Test
	void 상품주문() {
		// 상품 등록
		productService.addProduct(ProductSteps.상품등록요청_생성());
		// 상품 주문
		orderService.createOrder(OrderSteps.상품주문요청_생성());
		// 상품 결제
		final PaymentRequest request = PaymentSteps.주문결제요청_생성();
		
		paymentService.payment(request);
	}

}
