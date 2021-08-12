package products.service;

public interface ProductService {
    void add(ProductDTO productDTO);
    void delete(String productNumber);
    void update(String productNumber,ProductDTO productDTO);
    ProductDTO get(String productId);
}
