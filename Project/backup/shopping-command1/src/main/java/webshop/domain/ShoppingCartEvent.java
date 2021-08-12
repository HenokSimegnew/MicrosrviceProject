package webshop.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class ShoppingCartEvent {
    @Id
    private String id;
    private ShoppingCartEventType shoppingCartEventType;
    private ShoppingCart shoppingCart;
    private LocalDate Date;

    public ShoppingCartEvent() {
    }

    public ShoppingCartEvent(ShoppingCart shoppingCart, ShoppingCartEventType shoppingCartEventType) {
        this.shoppingCart = shoppingCart;
        this.shoppingCartEventType = shoppingCartEventType;
        this.Date = LocalDate.now();
    }


    public ShoppingCartEventType getShoppingCartEventType() {
        return shoppingCartEventType;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public LocalDate getDate() {
        return Date;
    }
}
