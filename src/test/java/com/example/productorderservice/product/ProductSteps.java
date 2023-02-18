package com.example.productorderservice.product;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductSteps {
	
	// API 요청이 오면 해당 HTTP
	public static ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
		return RestAssured.given().log().all() // 요청에 대한 로깅
				.contentType(MediaType.APPLICATION_JSON_VALUE) // Json 타입
				.body(request)
				.when()
				.post("/products")
				.then()
				.log().all().extract();
	}
		
	public static AddProductRequest 상품등록요청_생성() {
		final String name = "상품명";
		final int price = 1000;
		final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
		return new AddProductRequest(name, price, discountPolicy);
	}
	
	public static ExtractableResponse<Response> 상품조회요청(final Long productId) {
		return RestAssured.given().log().all()
				.when()
				.get("/products/{productId}", productId)
				.then().log().all()
				.extract();
	}
}
