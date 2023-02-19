package com.example.productorderservice.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;

class OrderApiTest extends ApiTest {
	
	@Test
	void 상품주문() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		
		final var response = OrderSteps.상품주문요청(OrderSteps.상품주문요청_생성());
		
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
