package webshop.services;

import webshop.domain.Order;
import webshop.dto.OrderDTO;
import webshop.dto.ShoppingCartDTO;

public interface OrderService {
    void addCustomer(String orderId, String customerId);

    OrderDTO createOrder(ShoppingCartDTO shoppingCartDTO);

    OrderDTO getOrder(String orderId);

    void placeOrder(String orderId);
}