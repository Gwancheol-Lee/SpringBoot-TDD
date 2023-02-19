package com.example.productorderservice.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import com.example.productorderservice.product.ProductSteps;

class OrderTest extends ApiTest {
	
	@Test
	void getTotalPrice() {
		final Order order = new Order(new Product("상품명", 2000, DiscountPolicy.FIX_1000_AMOUNT), 2);
		
		final int totalPrice = order.getTotalPrice();
		
		assertThat(totalPrice).isEqualTo(2000);
	}
}
