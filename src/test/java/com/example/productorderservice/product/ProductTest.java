package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
	void update() {
		final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
		
		product.update("상품 수정", 2000, DiscountPolicy.NONE);
		
		
		assertThat(product.getName()).isEqualTo("상품 수정");
		assertThat(product.getPrice()).isEqualTo(2000);
	}
}
