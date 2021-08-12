package webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webshop.dto.CustomerDTO;
import webshop.dto.ShoppingCartDTO;

@Document
public class Order {
    @Id
    private String id;
    private ShoppingCartDTO shoppingCart;
    private CustomerDTO customerDTO;
    private double totalPrice;
    private OrderStatus orderStatus;

    public Order(ShoppingCartDTO shoppingCart, double totalPrice) {
        this.shoppingCart = shoppingCart;
        this.totalPrice = totalPrice;
        this.orderStatus = OrderStatus.PENDING;
    }

    public Order(String id, ShoppingCartDTO shoppingCart, double totalPrice, OrderStatus orderStatus) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getId() {
        return id;
    }

    public ShoppingCartDTO getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartDTO shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
