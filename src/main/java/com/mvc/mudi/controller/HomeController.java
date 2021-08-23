package com.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mvc.mudi.model.OrderStatus;
import com.mvc.mudi.model.Orders;
import com.mvc.mudi.repository.OrderRepository;


@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping()
	private String home(Model model, Principal principal) {
		Sort sort = Sort.by("deliveryDate"); 
		PageRequest pagination = PageRequest.of(0, 10, sort);
		
		List<Orders> orders = orderRepository.findByStatus(OrderStatus.DELIVERED, pagination);  
		model.addAttribute("orders", orders);
		return "home"; 
	}
	  
}
