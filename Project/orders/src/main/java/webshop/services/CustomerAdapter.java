package webshop.services;

import webshop.domain.Customer;
import webshop.dto.CustomerDTO;

public class CustomerAdapter {
    public static Customer getCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(), customerDTO.getAddress(), customerDTO.getPhoneNumber());
    }

    public static CustomerDTO getCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }
}