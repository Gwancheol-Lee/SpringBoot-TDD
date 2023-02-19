package com.example.productorderservice.payment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.product.ProductSteps;

class PaymentApiTest extends ApiTest{
	
	@Test
	void 상품주문() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		OrderSteps.상품주문요청(OrderSteps.상품주문요청_생성());
		final var response = PaymentSteps.주문결제요청(PaymentSteps.주문결제요청_생성());
		
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

}
