package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ProductServiceTest { 
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductPort productPort;
	
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
	
	@Test
	void 상품수정() {
		productService.addProduct(ProductSteps.상품등록요청_생성());
		final Long productId = 1L;
		final UpdateProductRequest request = ProductSteps.상품수정요청_생성();
	
		// 상품 수정
		productService.updateProduct(productId, request);
		
		// 상품조회
		final ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
		final GetProductResponse productReponse = response.getBody();
		
		// 수정 검증 
		assertThat(productReponse.name()).isEqualTo("상품 수정");
		assertThat(productReponse.price()).isEqualTo(2000);
	}
	
}
