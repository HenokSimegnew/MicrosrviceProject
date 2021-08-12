package webshop.eventReceivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import webshop.domain.ShoppingCartEvent;
import webshop.repositories.ShoppingCartRepository;

@Service
public class AddToShoppingCartReceiver {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @KafkaListener(topics = {"addToShoppingCartTopic"})
    public void receive(@Payload ShoppingCartEvent shoppingCartEvent) {
        shoppingCartRepository.save(shoppingCartEvent.getShoppingCart());
    }
}
