package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;

public interface OrderPort {
	Product getProductById(final Long productId);
	void save(final Order order);
}
