package webshop.service;

import webshop.domain.Product;

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
