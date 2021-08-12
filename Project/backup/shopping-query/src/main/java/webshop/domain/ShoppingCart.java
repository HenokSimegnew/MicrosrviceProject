package webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    @Id
    private String cartId;
    private List<CartLine> cartLines;

    public ShoppingCart() {
        cartLines = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        for (CartLine cartLine : cartLines) {
            if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
                cartLine.setQuantity(cartLine.getQuantity() + quantity);
                return;
            }
        }
        CartLine cartLine = new CartLine(quantity, product);
        cartLines.add(cartLine);
    }


    public double getTotalPrice() {
        double totalPrice = 0.0;
        if (!cartLines.isEmpty())
            totalPrice = cartLines.stream().map(CartLine::getProduct).map(Product::getPrice).reduce(Double::sum).get();
        return totalPrice;
    }

    public void removeFromCart(Product product) {
        Iterator<CartLine> iter = cartLines.iterator();
        while (iter.hasNext()) {
            CartLine cartLine = iter.next();
            if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
                if (cartLine.getQuantity() > 1) {
                    cartLine.setQuantity(cartLine.getQuantity() - 1);
                } else {
                    iter.remove();
                }
            }
        }
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }
}
