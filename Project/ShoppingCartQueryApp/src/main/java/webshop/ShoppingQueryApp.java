package webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableMongoRepositories
@EnableFeignClients
@EnableDiscoveryClient
public class ShoppingQueryApp {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingQueryApp.class, args);
	}

}
