package com.example.productorderservice.product;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductSteps {
	
	public static AddProductRequest 상품등록요청_생성() {
		final String name = "상품명";
		final int price = 1000;
		final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
		return new AddProductRequest(name, price, discountPolicy);
	}
	
	public static ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
		return RestAssured.given().log().all() // 요청에 대한 로깅
				.contentType(MediaType.APPLICATION_JSON_VALUE) // Json 타입
				.body(request)
				.when()
				.post("/products")
				.then()
				.log().all().extract();
	}
		
	public static ExtractableResponse<Response> 상품조회요청(final Long productId) {
		return RestAssured.given().log().all()
				.when()
				.get("/products/{productId}", productId)
				.then().log().all()
				.extract();
	}
	
	public static UpdateProductRequest 상품수정요청_생성() {
		return new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
	}
	
	public static ExtractableResponse<Response> 상품수정요청(final Long productId, final UpdateProductRequest request) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(request)
				.when()
				.patch("/products/{productId}", productId)
				.then().log().all()
				.extract();
	}
}
