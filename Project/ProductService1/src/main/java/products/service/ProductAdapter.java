package products.service;


import products.domain.Product;

public class ProductAdapter {
    public static Product fromDTO(ProductDTO dto){
        return new Product(dto.getProductNumber(), dto.getName(), dto.getPrice(),dto.getDescription(),dto.getNumberInStock());
    }

    public static ProductDTO toDTO(Product product){
        return new ProductDTO(product.getProductNumber(), product.getName(), product.getPrice(),product.getDescription(),product.getNumberInStock());
    }
}
