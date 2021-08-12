package webshop.eventSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import webshop.domain.ShoppingCartEvent;

@Service
public class ShoppingCartEventSenderApp {
    @Autowired
    private KafkaTemplate<String, ShoppingCartEvent> kafkaTemplate;

    public void send(String topic, ShoppingCartEvent shoppingCartEvent) {
        kafkaTemplate.send(topic, shoppingCartEvent);
    }
}