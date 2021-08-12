package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.services.ShoppingCartDTO;
import webshop.services.ShoppingCartService;

@RestController
@RequestMapping("/CartQueries")
public class
ShoppingController {
	@Autowired
	ShoppingCartService shoppingService;

	@GetMapping("/{cartId}")
	public ResponseEntity<?> getCart(@PathVariable String cartId) {
		ShoppingCartDTO cart = shoppingService.getCart(cartId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
}
