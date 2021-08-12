package client;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {
    private String cartId;
    private double totalPrice;
    private List<CartLineDTO> cartLines;

    public ShoppingCartDTO() {
        this.cartLines = new ArrayList<>();
    }
    public ShoppingCartDTO(String cartId, double totalPrice) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.cartLines = new ArrayList<>();
    }

    public void print() {
        System.out.println("Content of the shoppingcart:");
        for (CartLineDTO cline : cartLines) {
            System.out.println("Quantity: "+cline.getQuantity() + " "
                    + cline.getProduct().getProductNumber() + " "
                    + cline.getProduct().getDescription() + " "
                    + cline.getProduct().getPrice());
        }
        System.out.println("Total price = "+totalPrice);
    }



    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartLineDTO> getCartLines() {
        return cartLines;
    }

    public void setCartLines(ArrayList<CartLineDTO> cartLines) {
        this.cartLines = cartLines;
    }

    public void addCartLine(CartLineDTO cartLine) {
        cartLines.add(cartLine);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "cartId='" + cartId + '\'' +
                ", totalPrice=" + totalPrice +
                ", cartLines=" + cartLines +
                '}';
    }
}
