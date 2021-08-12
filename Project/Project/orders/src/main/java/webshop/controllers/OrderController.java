package webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.services.ShoppingCartDTO;
import webshop.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return new ResponseEntity(orderService.createOrder(shoppingCartDTO),HttpStatus.OK);
    }

//    @HystrixCommand(fallbackMethod = "placeOrderFallback", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")})
    @PutMapping("/{orderId}/placeOrder")
    public ResponseEntity<?> placeOrder(@PathVariable String orderId) {
        orderService.placeOrder(orderId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @HystrixCommand(fallbackMethod = "addCustomerFallback", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")})
    @PutMapping("/{orderId}/customer/{customerId}")
    public ResponseEntity<?> addCustomer(@PathVariable String orderId, @PathVariable String customerId) {
        orderService.addCustomer(orderId, customerId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Fallback methods
    public ResponseEntity<?> placeOrderFallback(String orderId){
        return ResponseEntity.internalServerError().body("Server timeout error");
    }

    public ResponseEntity<?> addCustomerFallback(String orderId,String customerId){
        return ResponseEntity.internalServerError().body("Server timeout error");
    }
}
