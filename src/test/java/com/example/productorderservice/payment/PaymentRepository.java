package com.example.productorderservice.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
	
	private Map<Long, Payment> persistence = new HashMap<>();
	private Long sequence = 0L;
	
	public void save(final Payment payment) {
		payment.assignId(++sequence);
		persistence.put(payment.getId(), payment);
	}
}
