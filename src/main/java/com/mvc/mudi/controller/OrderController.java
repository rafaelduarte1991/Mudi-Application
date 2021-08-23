package com.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.mudi.dto.NewOrderRequest;
import com.mvc.mudi.model.Orders;
import com.mvc.mudi.model.User;
import com.mvc.mudi.repository.OrderRepository;
import com.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("form")
	public String form(NewOrderRequest request) {
		return "order/form"; 
	} 
	
	@PostMapping("new")
	public String newOrder(@Valid NewOrderRequest request, BindingResult result) {
		
		if(result.hasErrors()) {
			return "order/form";
		} 
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); 
		
		User user = userRepository.findByUsername(username);
		
		Orders order = request.toOrder();
		order.setUser(user); 
		 
		orderRepository.save(order);
		return "redirect:/home";
		
	}
}
