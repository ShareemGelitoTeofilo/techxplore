package com.acn.texchxplore.controller;

import com.acn.texchxplore.impl.RegularBill;
import org.springframework.beans.factory.annotation.Autowired;

import com.acn.texchxplore.domain.GroceryBill;
import com.acn.texchxplore.entity.ShoppingClerk;
import com.acn.texchxplore.impl.DiscountedBill;
import com.acn.texchxplore.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/items/bill")
public class GroceryBillController {

	@Autowired
	private ItemRepository itemRepo;
	private ShoppingClerk shoppingClerk;

	public GroceryBillController() {
		shoppingClerk = new ShoppingClerk("TechXplore");
	}

	@GetMapping("/discounted")
	public GroceryBill getTotalDiscountedBill() {
		GroceryBill grocery = new DiscountedBill(shoppingClerk);
		grocery.setItemList(itemRepo.findAll());
		return grocery;
	}

	@GetMapping("/regular")
	public GroceryBill getTotalRegularBill() {
		GroceryBill grocery = new RegularBill(shoppingClerk);
		grocery.setItemList(itemRepo.findAll());
		return grocery;
	}
}
