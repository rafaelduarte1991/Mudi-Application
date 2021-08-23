package com.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mudi.model.OrderStatus;
import com.mvc.mudi.model.Orders;
import com.mvc.mudi.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrdersRest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("pending")
	public List<Orders> getOrdersWaitingForOffers(){
		Sort sort = Sort.by("id").descending(); 
		PageRequest pagination = PageRequest.of(0, 3, sort);
		
		return orderRepository.findByStatus(OrderStatus.PENDING, pagination);
	}
}
