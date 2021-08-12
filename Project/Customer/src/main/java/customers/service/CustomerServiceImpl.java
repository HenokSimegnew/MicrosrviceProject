package customers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import customers.domain.Customer;
import customers.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void add(CustomerDTO customerDTO) {
        Customer customer = CustomerAdapter.fromDTO(customerDTO);
        System.out.println("DTO:"+customerDTO);
        System.out.println("customer:"+customer);
        customerRepository.save(customer);
       // jmsSender.sendMessage(new CustomerChangeEventDTO("add", customerDTO));
    }

    @Override
    public void delete(String customerId) {
        customerRepository.deleteById(customerId);
      //  jmsSender.sendMessage(new CustomerChangeEventDTO("delete", new CustomerDTO(customerId,"",0.0,"",0.0)));
    }
    @Override
    public void update(String customerNumber, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerById(customerNumber);
        if(optionalCustomer.isPresent()){
            Customer customer = CustomerAdapter.fromDTO(customerDTO);
            customerRepository.save(customer);
           // jmsSender.sendMessage(new CustomerChangeEventDTO("update", customerDTO));
        }
    }
    @Override
    public CustomerDTO get(String customerNumber) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerNumber);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            return CustomerAdapter.toDTO(customer);
        }
        return null;
    }

}


