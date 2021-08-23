package com.mvc.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mudi.dto.NewOfferRequest;
import com.mvc.mudi.model.Offer;
import com.mvc.mudi.model.Orders;
import com.mvc.mudi.repository.OrderRepository;

@RestController
@RequestMapping("/api/offers")
public class OffersRest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@PostMapping
	public Offer createOffer(@Valid @RequestBody NewOfferRequest request) {
		Optional<Orders> fetchedRequest = orderRepository.findById(request.getOrderId());
		if(!fetchedRequest.isPresent()) {
			return null;
		}
		
		Orders order = fetchedRequest.get();
		
		Offer newOffer = request.toOffer();	
		newOffer.setOrder(order);
		order.getOffers().add(newOffer);
		orderRepository.save(order);
		
		return newOffer;
	}
}
