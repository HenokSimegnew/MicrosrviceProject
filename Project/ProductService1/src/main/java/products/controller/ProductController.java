package products.controller;

import products.service.ProductDTO;
import products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> add(@RequestBody ProductDTO productDTO){
        productService.add(productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{productNumber}")
    public ResponseEntity<?> update(@PathVariable String productNumber,@RequestBody ProductDTO productDTO){
        productService.update(productNumber,productDTO);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @DeleteMapping("/products/{productNumber}")
    public ResponseEntity<?> delete(@PathVariable String productNumber){
        productService.delete(productNumber);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/products/{product_id}")
    public ResponseEntity<?> get(@PathVariable String product_id){
        ProductDTO productDto = productService.get(product_id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
