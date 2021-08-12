package customers.service;


import customers.domain.Customer;

public class CustomerAdapter {
    public static Customer fromDTO(CustomerDTO dto){
        return new Customer(dto.getId(), dto.getFirstName(), dto.getLastName(),dto.getEmail(), dto.getPhoneNumber());
      //  return new Customer(dto.getCustomerNumber(), dto.getFirstname(), dto.getLastname(),dto.getEmail(),dto.getPhone());
    }

    public static CustomerDTO toDTO(Customer product){
        //return new CustomerDTO(product.getCustomerNumber(), product.getFirstname(), product.getLastname(),product.getEmail(),product.getPhone());
        return new CustomerDTO(product.getId(), product.getFirstname(), product.getLastname(),product.getEmail(),product.getAddress(),product.getPhone());
    }
}
