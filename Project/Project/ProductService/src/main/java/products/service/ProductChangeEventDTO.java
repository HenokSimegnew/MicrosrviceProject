package products.service;

import products.domain.Product;

public class ProductChangeEventDTO {
    private String change;
    private ProductDTO product;

    public ProductChangeEventDTO() {
    }

    public ProductChangeEventDTO(String change, ProductDTO product) {
        this.change = change;
        this.product = product;
    }

    public String getChange() {
        return change;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
