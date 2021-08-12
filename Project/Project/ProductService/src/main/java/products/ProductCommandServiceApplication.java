package products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableJms
@EnableFeignClients
@EnableDiscoveryClient
@EnableMongoRepositories
@EnableKafka

public class ProductCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCommandServiceApplication.class, args);
    }
}