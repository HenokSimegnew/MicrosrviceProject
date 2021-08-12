package webshop.service;

import java.util.ArrayList;
import java.util.List;

public class CreateShoppingCartDTO {
    private List<CartLineDTO> cartLines;

    public CreateShoppingCartDTO() {
        this.cartLines = new ArrayList<>();
    }

    public CreateShoppingCartDTO(List<CartLineDTO> cartLines) {
        this.cartLines = cartLines;
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
}
