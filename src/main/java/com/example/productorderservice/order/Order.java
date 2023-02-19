package com.example.productorderservice.order;

import org.springframework.util.Assert;

import com.example.productorderservice.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Product product;
	private int quantity;
	
	public Order(final Product product, final int quantity) {
		Assert.notNull(product, "상품은 필수입니다.");
		Assert.isTrue(quantity>0, "수량은 0보다 커야 합니다.");
		this.product = product;
		this.quantity = quantity;
	}
		
}