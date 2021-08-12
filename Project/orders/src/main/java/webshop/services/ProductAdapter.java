package webshop.services;

import webshop.domain.Product;
import webshop.dto.ProductDTO;

public class ProductAdapter {
    public static Product getProduct(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getName(),
                productDTO.getProductNumber(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getNumberInStock()
        );
        return product;
    }

    public static ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(
                product.getName(),
                product.getProductNumber(),
                product.getDescription(),
                product.getPrice(),
                product.getNumberInStock()
        );
        return productDTO;
    }
}