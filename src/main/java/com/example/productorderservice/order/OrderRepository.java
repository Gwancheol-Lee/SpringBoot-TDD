package com.example.productorderservice.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
	private final
	Map<Long, Order> persistence = new HashMap<>();
	private Long sequence = 0L;
	
	public void save(final Order order) {
		order.assignId(++sequence);
		persistence.put(order.getId(), order);
	}
}