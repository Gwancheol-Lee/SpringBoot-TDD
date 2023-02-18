package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.example.productorderservice.ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SpringBootTest
class ProductServiceTest { 
	
	@Autowired
	ProductService productService;
	
	@Test
	void 상품조회() {
		// 상품등록
		productService.addProduct(ProductSteps.상품등록요청_생성());
		final long productId = 1L;
		
		// 상품조회
		final ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
		
		// 상품응답 검증
		assertThat(response).isNotNull();
	}
	
}
