package webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.domain.ShoppingCart;
import webshop.repositories.ShoppingCartRepository;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartDTO getCart(String cartId) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
        if (cart.isPresent())
            return ShoppingCartAdapter.getShoppingCartDTO(cart.get());
        else
            return null;
    }

}
