package webshop.dto;

import webshop.domain.OrderStatus;
import webshop.domain.ShoppingCart;

public class OrderDTO {
    private String id;
    private ShoppingCartDTO shoppingCart;
    private CustomerDTO customerDTO;
    private double totalPrice;
    private OrderStatus orderStatus;

    public OrderDTO(String id, ShoppingCartDTO shoppingCart, double totalPrice, CustomerDTO customerDTO, OrderStatus orderStatus) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.totalPrice = totalPrice;
        this.customerDTO = customerDTO;
        this.orderStatus = orderStatus;
    }

    public OrderDTO() {
    }

    public String getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public ShoppingCartDTO getShoppingCart() {
        return shoppingCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
}
