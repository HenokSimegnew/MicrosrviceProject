package webshop.service;

public interface ShoppingCartService {
    void createShoppingCart(CreateShoppingCartDTO createShoppingCartDTO);

    void removeProductFromShoppingCart(String cartId, ProductDTO productDTO);

    void changeQuantity(String shoppingCartId, String productNumber, int quantity);

    void addToShoppingCart(String cartId, ProductDTO productDto, int quantity);

    OrderDTO createOrder(String cartId);

}
