package products.service;

import products.domain.Product;
import products.integration.JmsSender;
import products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JmsSender jmsSender;

    @Override
    public void add(ProductDTO productDTO) {
        Product product = ProductAdapter.fromDTO(productDTO);
        System.out.println("DTO:"+productDTO);
        System.out.println("product:"+product);
        productRepository.save(product);
        jmsSender.sendMessage(new ProductChangeEventDTO("add", productDTO));
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(productId);
        jmsSender.sendMessage(new ProductChangeEventDTO("delete", new ProductDTO(productId,"",0.0,"",0.0)));
    }
    @Override
    public void update(String productNumber, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findProductByProductNumber(productNumber);
        if(optionalProduct.isPresent()){
            Product product = ProductAdapter.fromDTO(productDTO);
            productRepository.save(product);
            jmsSender.sendMessage(new ProductChangeEventDTO("update", productDTO));
        }
    }
    @Override
    public ProductDTO get(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return ProductAdapter.toDTO(product);
        }
        return null;
    }

}


