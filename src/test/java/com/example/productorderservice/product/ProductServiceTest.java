package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ProductServiceTest {
	
	private ProductService productService;
	
	@Test
	void 상품등록() {
		final String name = "상품명";
		final int price = 1000;
		final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
		final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
		productService.addProduct(request);
	}
	
	// 레코드 클래스는 불변 데이터 객체를 쉽게 생성할 수 있도록 해줌. 생성자 선언, getter, setter, toString, equals, hashCode 등의 메소드 생략 가능 
	private record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
		private AddProductRequest {
			Assert.hasText(name, "상품명은 필수입니다.");
			Assert.isTrue(price>0, "상품 가격은 0보다 커야 합니다.");
			Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
		}
	}
	// 기존 아래와 같이 생성자 선언 할 필요 없이 위 레코드 클래스로 코드를 깔끔하게 작성할 수 있다.
	/*
	private static class AddProductRequest {

		private final String name;
		private final int price;
		private final DiscountPolicy discountPolicy;
		
		public AddProductRequest(final String name, final int price, final DiscountPolicy discountPolicy) {
			this.name = name;
			this.price = price;
			this.discountPolicy = discountPolicy;
			Assert.hasText(name, "상품명은 필수입니다.");
			Assert.isTrue(price>0, "상품 가격은 0보다 커야 합니다.");
			Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
		}
		
	}
	*/
	
	private class ProductService {
		
	}
	
	private enum DiscountPolicy {
		NONE
	}
}
