package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.productorderservice.product.domain.DiscountPolicy;

class DiscountPolicyTest {
	
	@Test
	void applyDiscount() {
		final int price = 1000;
		
		final int discountedPrice = DiscountPolicy.NONE.applyDiscount(price);
		
		assertThat(discountedPrice).isEqualTo(price);
	}
	
	@Test
	void fix_1000_discounted_price() {
		final int price = 2000;
		
		// 1000원 고정 할인 정책 적용 시 price - 1000
		final int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
		
		assertThat(discountedPrice).isEqualTo(1000);
	}
	
	@Test
	void over_discounted_price() {
		final int price = 500;
		
		// 500원보다 더 할인했을시 0원인지 확인
		final int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
		
		assertThat(discountedPrice).isEqualTo(0);
	}
}
