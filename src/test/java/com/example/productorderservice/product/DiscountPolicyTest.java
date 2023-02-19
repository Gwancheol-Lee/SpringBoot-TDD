package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DiscountPolicyTest {
	
	@Test
	void applyDiscount() {
		final int price = 1000;
		
		final int discountedPrice = DiscountPolicy.NONE.applyDiscount(price);
		
		assertThat(discountedPrice).isEqualTo(price);
	}
	
	@Test
	void name() {
		final int price = 2000;
		
		// 1000원 고정 할인 정책 적용 시 price - 1000
		final int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
		
		assertThat(discountedPrice).isEqualTo(1000);
	}
}
