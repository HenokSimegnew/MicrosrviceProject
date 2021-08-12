package customers.service;

public interface CustomerService {
    void add(CustomerDTO customerDTO);
    void delete(String customerNumber);
    void update(String customerNumber,CustomerDTO customerDTO);
    CustomerDTO get(String customerNumber);
}
