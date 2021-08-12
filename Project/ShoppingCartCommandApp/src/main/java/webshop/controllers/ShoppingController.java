package webshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger =
            LoggerFactory.getLogger(ShoppingController.class.getName());

    @PostMapping("/{cartId}/order/")
    public ResponseEntity<?> createOrder(@PathVariable String cartId) {
        logger.info("Add cart with order :");
        return new ResponseEntity(shoppingService.createOrder(cartId),HttpStatus.OK);
    }

    @PostMapping(value = "/{cartId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable String cartId, @PathVariable int quantity, @RequestBody ProductDTO productDto) {
        shoppingService.addToShoppingCart(cartId, productDto, quantity);
        logger.info("Add Shaopingcart with products and quantity of the product  :");
        return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createShoppingCart(@RequestBody CreateShoppingCartDTO createShoppingCartDTO) {
        shoppingService.createShoppingCart(createShoppingCartDTO);
        logger.info("Create Shaopingcart with products :");
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
        logger.info("Change Shaoping cart product quantity :");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
