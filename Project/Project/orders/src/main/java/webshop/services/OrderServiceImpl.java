package webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webshop.domain.Order;
import webshop.domain.OrderStatus;
import webshop.eventSender.OrderPlacedEventSenderApp;
import webshop.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private OrderPlacedEventSenderApp orderPlacedEventSenderApp;

    @Override
    public void addCustomer(String orderId, String customerId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            CustomerDTO customerDTO = customerServiceClient.getCustomer(customerId);
            if (customerDTO != null) {
                order.setCustomerDTO(customerDTO);
                orderRepository.save(order);
            } else {
                System.out.println("Customer doesn't exist");
            }
        }
    }

    @Override
    public OrderDTO createOrder(ShoppingCartDTO shoppingCartDTO) {
        Order order = new Order(shoppingCartDTO, shoppingCartDTO.getTotalPrice());
        orderRepository.save(order);
        return OrderAdapter.getOrderDTO(order);
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            OrderDTO orderDTO = OrderAdapter.getOrderDTO(orderOptional.get());
            return orderDTO;
        }
        return null;
    }

    @Override
    public void placeOrder(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            List<CartLineDTO> cartLines = order.getShoppingCart().getCartLines();
            for (CartLineDTO cartLine : cartLines) {
                ProductDTO product = cartLine.getProduct();
                product.setNumberInStock(product.getNumberInStock() - cartLine.getQuantity());
                productServiceClient.updateProduct(product.getProductNumber(), product);
            }
            order.setOrderStatus(OrderStatus.PLACED);
            OrderDTO orderDTO = OrderAdapter.getOrderDTO(order);
            orderPlacedEventSenderApp.send("orderPlacedTopic", orderDTO);
            orderRepository.save(order);
        }
    }

    @FeignClient(value = "CustomerService")
    interface CustomerServiceClient {
        @RequestMapping(value = "/customers/{customerId}")
        CustomerDTO getCustomer(@PathVariable String customerId);
    }

    @FeignClient(value = "ProductService")
    interface ProductServiceClient {
        @RequestMapping(value = "/products/{productNumber}", method = RequestMethod.PUT)
        void updateProduct(@PathVariable String productNumber, @RequestBody ProductDTO productDTO);
    }
}