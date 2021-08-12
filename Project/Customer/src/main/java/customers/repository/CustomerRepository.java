package customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import customers.domain.Customer;

import java.util.Optional;


@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    Optional<Customer> findCustomerById(String s);

}
