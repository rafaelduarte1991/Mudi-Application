package com.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mvc.mudi.model.OrderStatus;
import com.mvc.mudi.model.Orders;
import com.mvc.mudi.repository.OrderRepository;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("order")
	private String home(Model model, Principal principal) {
		
		List<Orders> orders = orderRepository.findByUser(principal.getName());  
		model.addAttribute("orders", orders);
		return "user/home"; 
	}
	@GetMapping("order/{status}")
	private String byStatus(@PathVariable("status") String status, Model model, Principal principal) {
		
		List<Orders> orders = orderRepository.findByStatusAndUser(OrderStatus.valueOf(status.toUpperCase()), principal.getName()); 
		model.addAttribute("orders", orders);
		model.addAttribute("status", status);
		return "user/home"; 
	}
	@ExceptionHandler(IllegalArgumentException.class) 
	public String onError() {
		return "redirect:/user/home";
	}
}
