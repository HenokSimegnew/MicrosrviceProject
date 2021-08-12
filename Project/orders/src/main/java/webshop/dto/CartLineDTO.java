package webshop.dto;

public class CartLineDTO {
    private int quantity;
    private ProductDTO product;

    public CartLineDTO() {
    }

    public CartLineDTO(int quantity, ProductDTO product) {
        this.quantity = quantity;
        this.product = product;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
