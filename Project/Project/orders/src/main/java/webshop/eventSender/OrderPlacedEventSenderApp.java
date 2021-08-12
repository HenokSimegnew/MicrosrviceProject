package webshop.eventSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import webshop.services.OrderDTO;

@Service
public class OrderPlacedEventSenderApp {
    @Autowired
    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void send(String topic, OrderDTO orderDTO) {
        kafkaTemplate.send(topic, orderDTO);
    }
}
