package com.example.productorderservice.product;

class ProductService {
	private ProductPort productPort;
	
	ProductService(ProductPort productPort) {
		this.productPort = productPort;
	}

	public void addProduct(final AddProductRequest request) {
		final Product product = new Product(request.name(), request.price(), request.discountPolicy());
		
		productPort.save(product);
		// throw new UnsupportedOperationException("Unsupported addProduct");
	}
}