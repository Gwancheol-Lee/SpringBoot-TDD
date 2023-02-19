package com.example.productorderservice.order;

import org.springframework.stereotype.Component;

import com.example.productorderservice.product.Product;
import com.example.productorderservice.product.ProductRepository;

@Component
public class OrderAdapter implements OrderPort {
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	
	public OrderAdapter(final ProductRepository productRepository, final OrderRepository orderRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}
	
	@Override
	public Product getProductById(final Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
	}
	
	@Override
	public void save(final Order order) {
		orderRepository.save(order);
	}
}
