package client;

public class StockDTO {
    private String productId;
    private int numberInStock;

    public StockDTO(){}

    public StockDTO(String productId, int numberInStock) {
        this.productId = productId;
        this.numberInStock = numberInStock;
    }

    public String getProductId() {
        return productId;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "productId='" + productId + '\'' +
                ", numberInStock=" + numberInStock +
                '}';
    }
}
