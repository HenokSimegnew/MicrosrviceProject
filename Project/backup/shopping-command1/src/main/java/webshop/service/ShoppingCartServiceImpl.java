package webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webshop.domain.*;
import webshop.eventSender.ShoppingCartEventSender;
import webshop.repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingQueryServiceClient shoppingQueryServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartEventSender shoppingCartEventSender;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Override
    public void createShoppingCart(CreateShoppingCartDTO createShoppingCartDTO) {
        ShoppingCart shoppingCart = ShoppingCartAdapter.getShoppingCartFromCreateShoppingCartDTO(createShoppingCartDTO);
        if (!shoppingCart.getCartLines().isEmpty()) {
            for (CartLine cartLine : shoppingCart.getCartLines()) {
                ProductDTO productDTO = productServiceClient.getProduct(cartLine.getProduct().getProductNumber());
                if (productDTO.getNumberInStock() > cartLine.getQuantity()) {
                    ShoppingCartEvent shoppingCartEvent = new ShoppingCartEvent(shoppingCart, ShoppingCartEventType.CREATE_CART);
                    shoppingCartRepository.save(shoppingCartEvent);
                    shoppingCartEventSender.send("createShoppingCartTopic", shoppingCartEvent);
                } else
                    System.out.println("Error Not enough product in stock");
            }
        } else {
            ShoppingCartEvent shoppingCartEvent = new ShoppingCartEvent(shoppingCart, ShoppingCartEventType.CREATE_CART);
            shoppingCartRepository.save(shoppingCartEvent);
            shoppingCartEventSender.send("createShoppingCartTopic", shoppingCartEvent);
        }
    }

    @Override
    public void removeProductFromShoppingCart(String cartId, ProductDTO productDTO) {
        Product product = ProductAdapter.getProduct(productDTO);
        ShoppingCartDTO shoppingCartDTO = shoppingQueryServiceClient.getCart(cartId);
        if (shoppingCartDTO != null && product != null) {
            ShoppingCart shoppingCart = ShoppingCartAdapter.getShoppingCart(shoppingCartDTO);
            shoppingCart.removeFromCart(product);
            ShoppingCartEvent shoppingCartEvent = new ShoppingCartEvent(shoppingCart, ShoppingCartEventType.REMOVE_PRODUCT);
            shoppingCartRepository.save(shoppingCartEvent);
            shoppingCartEventSender.send("removeProductFromShoppingCartTopic", shoppingCartEvent);
        } else if (product == null) {
            System.out.println("Product doesn't exist");
        } else {
            System.out.println("Shopping cart not found");
        }
    }

    @Override
    public void changeQuantity(String shoppingCartId, String productNumber, int quantity) {
        ProductDTO productDTO = productServiceClient.getProduct(productNumber);
        ShoppingCartDTO shoppingCartDTO = shoppingQueryServiceClient.getCart(shoppingCartId);
        if (shoppingCartDTO != null && productDTO != null) {
            Product product = ProductAdapter.getProduct(productDTO);
            ShoppingCart shoppingCart = ShoppingCartAdapter.getShoppingCart(shoppingCartDTO);
            CartLine cartLine = shoppingCart.getCartLines().stream().filter(c -> c.getProduct().getProductNumber().equals(productNumber)).findAny().get();
            // Check if enough product in stock
            if (product.getNumberInStock() > (cartLine.getQuantity() + quantity)) {
                shoppingCart.addToCart(product, quantity);
                ShoppingCartEvent shoppingCartEvent = new ShoppingCartEvent(shoppingCart, ShoppingCartEventType.CHANGE_QUANTITY);
                shoppingCartRepository.save(shoppingCartEvent);
                shoppingCartEventSender.send("changeProductQuantityTopic", shoppingCartEvent);
            } else {
                System.out.println("Not enough product in stock");
            }
        } else if (productDTO == null) {
            System.out.println("Product doesn't exist");
        } else {
            System.out.println("Shopping cart not found");
        }
    }

    public void addToShoppingCart(String cartId, ProductDTO productDto, int quantity) {
        Product product = ProductAdapter.getProduct(productDto);
        ProductDTO productData = productServiceClient.getProduct(productDto.getProductNumber());
        ShoppingCartDTO shoppingCartDTO = shoppingQueryServiceClient.getCart(cartId);
        if (shoppingCartDTO != null) {
            ShoppingCart shoppingCart = ShoppingCartAdapter.getShoppingCart(shoppingCartDTO);
            if (productData.getNumberInStock() > quantity) {
                shoppingCart.addToCart(product, quantity);
                ShoppingCartEvent shoppingCartEvent = new ShoppingCartEvent(shoppingCart, ShoppingCartEventType.ADD_PRODUCT);
                shoppingCartRepository.save(shoppingCartEvent);
                shoppingCartEventSender.send("addToShoppingCartTopic", shoppingCartEvent);
            } else {
                System.out.println("Not enough product in stock");
            }
        } else {
            ShoppingCart cart = new ShoppingCart();
            cart.setCartId(cartId);
            cart.addToCart(product, quantity);
            ShoppingCartEvent shoppingCartEvent = new ShoppingCartEvent(cart, ShoppingCartEventType.ADD_PRODUCT);
            shoppingCartRepository.save(shoppingCartEvent);
            shoppingCartEventSender.send("addToShoppingCartTopic", shoppingCartEvent);
        }
    }

    @Override
    public void createOrder(String cartId) {
        ShoppingCartDTO shoppingCartDTO = shoppingQueryServiceClient.getCart(cartId);
        if (shoppingCartDTO != null) {
            orderServiceClient.createOrder(shoppingCartDTO);
        } else
            System.out.println("Shopping cart not found");
    }

    @FeignClient(name = "ShoppingQueryService")
    interface ShoppingQueryServiceClient {
        @RequestMapping("/CartQueries/{cartId}")
        ShoppingCartDTO getCart(@PathVariable("cartId") String cartId);
    }

    @FeignClient(name = "ProductService")
    interface ProductServiceClient {
        @RequestMapping("/products/{product_id}")
        ProductDTO getProduct(@PathVariable String product_id);
    }

    @FeignClient(name = "OrderService")
    interface OrderServiceClient {
        @RequestMapping(value = "/orders/", method = RequestMethod.POST)
        void createOrder(@RequestBody ShoppingCartDTO shoppingCartDTO);
    }
}
