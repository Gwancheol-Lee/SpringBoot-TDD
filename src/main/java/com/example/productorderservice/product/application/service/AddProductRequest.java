package com.example.productorderservice.product.application.service;

import org.springframework.util.Assert;

import com.example.productorderservice.product.domain.DiscountPolicy;

// 레코드 클래스는 불변 데이터 객체를 쉽게 생성할 수 있도록 해줌. 생성자 선언, getter, setter, toString, equals, hashCode 등의 메소드 생략 가능 
public record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
	public AddProductRequest {
		Assert.hasText(name, "상품명은 필수입니다.");
		Assert.isTrue(price>0, "상품 가격은 0보다 커야 합니다.");
		Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
	}
}