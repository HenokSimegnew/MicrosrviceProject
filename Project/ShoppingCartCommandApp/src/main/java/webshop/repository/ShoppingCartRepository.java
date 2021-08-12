package webshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webshop.domain.ShoppingCartEvent;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEvent, String> {
}
