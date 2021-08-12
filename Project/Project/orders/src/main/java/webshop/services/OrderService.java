package webshop.services;

public interface OrderService {
    void addCustomer(String orderId, String customerId);

    OrderDTO createOrder(ShoppingCartDTO shoppingCartDTO);

    OrderDTO getOrder(String orderId);

    void placeOrder(String orderId);
}