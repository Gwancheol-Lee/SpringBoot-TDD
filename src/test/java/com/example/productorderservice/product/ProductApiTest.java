package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.productorderservice.ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest { 
	
	@Test
	void 상품등록() {
		final var request = ProductSteps.상품등록요청_생성();
		
		final var response = ProductSteps.상품등록요청(request);
		
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
	
	@Test
	void 상품조회() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		Long productId = 1L;
		
		final var response = ProductSteps.상품조회요청(productId);
		
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
	}
	
	@Test
	void 상품수정() {
		
	}
}
