package webshop.service;

import webshop.domain.CartLine;
import webshop.domain.ShoppingCart;

public class ShoppingCartAdapter {
    public static ShoppingCart getShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(shoppingCartDTO.getCartId());
        for (CartLineDTO cartLine : shoppingCartDTO.getCartLines()) {
            shoppingCart.addToCart(ProductAdapter.getProduct(cartLine.getProduct()), cartLine.getQuantity());
        }
        return shoppingCart;
    }
   public static ShoppingCart getShoppingCartFromCreateShoppingCartDTO(CreateShoppingCartDTO createShoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        for (CartLineDTO cartLine : createShoppingCartDTO.getCartLines()) {
            shoppingCart.addToCart(ProductAdapter.getProduct(cartLine.getProduct()), cartLine.getQuantity());
        }
        return shoppingCart;
    }

    public static ShoppingCartDTO getShoppingCartDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO(shoppingCart.getCartId(), shoppingCart.getTotalPrice());
        for (CartLine cartLine : shoppingCart.getCartLines()) {
            CartLineDTO cartLineDTO = new CartLineDTO(cartLine.getQuantity(), ProductAdapter.getProductDTO(cartLine.getProduct()));
            shoppingCartDTO.addCartLine(cartLineDTO);
        }
        return shoppingCartDTO;
    }
}
