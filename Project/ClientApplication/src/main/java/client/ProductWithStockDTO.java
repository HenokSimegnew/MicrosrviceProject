package client;

public class ProductWithStockDTO {
    private String productNumber;
    private String name;
    private double price;
    private int numberInStock;

    public ProductWithStockDTO(){}

    public ProductWithStockDTO(String productNumber, String name, double price, int numberInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
    }


    public String getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    @Override
    public String toString() {
        return "ProductWithStockDTO{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", numberInStock=" + numberInStock +
                '}';
    }
}
