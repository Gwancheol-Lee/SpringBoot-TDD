package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.productorderservice.ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest { 
	
	@Test
	void 상품등록() {
		final var request = 상품등록요청_생성();
		
		final var response = 상품등록요청(request);
		
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
	// API 요청이 오면 해당 HTTP
	private final ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
		return RestAssured.given().log().all() // 요청에 대한 로깅
		.contentType(MediaType.APPLICATION_JSON_VALUE) // Json 타입
		.body(request)
		.when()
		.post("/products")
		.then()
		.log().all() // 응답에 대한 로깅
		.extract();
	}
		
	private static AddProductRequest 상품등록요청_생성() {
		final String name = "상품명";
		final int price = 1000;
		final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
		return new AddProductRequest(name, price, discountPolicy);
	}
}
