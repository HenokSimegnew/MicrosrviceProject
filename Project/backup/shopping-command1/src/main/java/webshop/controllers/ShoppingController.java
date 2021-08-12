package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.service.CreateShoppingCartDTO;
import webshop.service.ProductDTO;
import webshop.service.ShoppingCartDTO;
import webshop.service.ShoppingCartService;

@RestController
@RequestMapping("/CartCommands")
public class ShoppingController {
    @Autowired
    ShoppingCartService shoppingService;

    @PostMapping("/{cartId}/order/")
    public ResponseEntity<?> createOrder(@PathVariable String cartId) {
        shoppingService.createOrder(cartId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/{cartId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable String cartId, @PathVariable int quantity, @RequestBody ProductDTO productDto) {
        shoppingService.addToShoppingCart(cartId, productDto, quantity);
        return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createShoppingCart(@RequestBody CreateShoppingCartDTO createShoppingCartDTO) {
        shoppingService.createShoppingCart(createShoppingCartDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{cartId}/product/")
    public ResponseEntity<?> removeProductFromShoppingCart(@PathVariable String cartId, @RequestBody ProductDTO productDTO) {
        shoppingService.removeProductFromShoppingCart(cartId, productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{cartId}/product/{productNumber}/quantity/{quantity}")
    public ResponseEntity<?> changeQuantity(@PathVariable String cartId, @PathVariable int quantity, @PathVariable String productNumber) {
        shoppingService.changeQuantity(cartId, productNumber, quantity);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
