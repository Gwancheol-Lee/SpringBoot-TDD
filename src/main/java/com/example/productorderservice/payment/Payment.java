package com.example.productorderservice.payment;

import org.springframework.util.Assert;

import com.example.productorderservice.order.Order;

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
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Order order;
	private String cardNumber;
	
	public Payment(final Order order, final String cardNumber) {
		Assert.notNull(order, "주문은 필수입니다.");
		Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
		this.order = order;
		this.cardNumber = cardNumber;
	}
	
	public int getPrice() {
		return order.getTotalPrice();
	}
}
