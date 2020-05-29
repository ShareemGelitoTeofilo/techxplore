package com.acn.texchxplore.controller;

import com.acn.texchxplore.configuration.ServerAppConfiguration;
import com.acn.texchxplore.model.GroceryBill;
import com.acn.texchxplore.model.ShoppingClerk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/techxplore")
@RibbonClient(name = "webservice", configuration = ServerAppConfiguration.class)
public class GroceryBillController {

	@Autowired
	private RestTemplate restTemplate;

    @LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping("/grocery")
	public String getIndexPage(Model model) {
		GroceryBill regularBill = this.restTemplate.getForObject("http://webservice/items/bill/regular", GroceryBill.class);
		GroceryBill discountedBill = this.restTemplate.getForObject("http://webservice/items/bill/discounted", GroceryBill.class);

		ShoppingClerk clerk = regularBill.getClerk();

		model.addAttribute("clerk", clerk);
		model.addAttribute("regularBill", regularBill);
		model.addAttribute("discountedBill", discountedBill);
		return "grocery";
	}

}
