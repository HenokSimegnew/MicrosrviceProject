package webshop.services;

import webshop.domain.Order;
import webshop.dto.OrderDTO;

public class OrderAdapter {
    public static OrderDTO getOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getShoppingCart(), order.getTotalPrice(), order.getCustomerDTO(), order.getOrderStatus());
        return orderDTO;
    }

    public static Order getOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getId(), orderDTO.getShoppingCart(), orderDTO.getTotalPrice(), orderDTO.getOrderStatus());
        return order;
    }
}
