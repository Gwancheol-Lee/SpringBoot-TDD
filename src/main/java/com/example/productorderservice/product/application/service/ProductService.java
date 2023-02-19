package com.example.productorderservice.product.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productorderservice.product.application.port.ProductPort;
import com.example.productorderservice.product.domain.Product;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
public class ProductService {
	
	private final ProductPort productPort;
	
	ProductService(final ProductPort productPort) {
		this.productPort = productPort;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Void> addProduct(
			@RequestBody final AddProductRequest request) {
		final Product product = new Product(request.name(), request.price(), request.discountPolicy());
		
		productPort.save(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<GetProductResponse> getProduct(final Long productId) {
		final Product product = productPort.getProduct(productId);
		
		final GetProductResponse response = new GetProductResponse(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getDiscountPolicy());
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{productId}")
	@Transactional
	public ResponseEntity<Void> updateProduct(
			@PathVariable final Long productId, 
			@RequestBody UpdateProductRequest request) {
		
		final Product product = productPort.getProduct(productId);
		product.update(request.name(), request.price(), request.discountPolicy());
		
		productPort.save(product);
		return ResponseEntity.ok().build();
	}
	
	/*
	 * HttpServletRequest에서 쿠키를 추키의 0번째 Name을 추출하여 사용하고 할 때는 아래와 같이 미리 final로 선언해두고 아래 메소드 호출하는 방식으로 사용
	 * 
	@GetMapping("/{productId}")
	public ResponseEntity<GetProductResponse> getProduct(
			@PathVariable final Long productId,
			HttpServletRequest request) {
		// 쿠키의 0번째 데이터 추출
		final Cookie cookie = request.getCookies()[0];
		// 0번째 데이터의 Name 선언
		final String name = cookie.getName();
		// 메소드에 name 매개변수 같이 포함하여 호출
		return getProduct(productId, name);
	}
	
	ResponseEntity<GetProductResponse> getProduct(final Long productId, final String name) {
		
		// 매개변수로 넘겨받은 name 사용
		
		final Product product = productPort.getProduct(productId);
		
		final GetProductResponse response = new GetProductResponse(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getDiscountPolicy());
		
		return ResponseEntity.ok(response);
	}
	*/
	
}