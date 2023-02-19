package com.example.productorderservice.order.adapter;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productorderservice.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}