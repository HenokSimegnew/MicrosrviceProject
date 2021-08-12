package customers.controller;

import customers.service.CustomerDTO;
import customers.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class.getName());

    @PostMapping("/customers")
    public ResponseEntity<?> add(@RequestBody CustomerDTO customerDTO){
        customerService.add(customerDTO);
        logger.info("Calling add customer and add this customer: :"+ customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }


    @PutMapping("/customers/{customerNumber}")
    public ResponseEntity<?> update(@PathVariable String customerNumber,@RequestBody CustomerDTO customerDTO){
        customerService.update(customerNumber,customerDTO);
        logger.info("Calling update customer using customer no :"+ customerNumber);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerNumber}")
    public ResponseEntity<?> delete(@PathVariable String customerNumber){
        customerService.delete(customerNumber);
        logger.info("Calling delete customer using customer no :"+ customerNumber);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers/{customer_id}")
    public ResponseEntity<?> get(@PathVariable String customer_id){
        CustomerDTO customerDto = customerService.get(customer_id);
        logger.info("Calling get customer using customer id : " + customer_id);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }


}
