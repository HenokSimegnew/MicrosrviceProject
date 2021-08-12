package products.service;

public class ProductDTO {
    private String productNumber;
    private String name;
    private double price;
    private String description;
    private double numberInStock;

    public ProductDTO(){}

    public ProductDTO(String productNumber, String name, double price,String description, double numberInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberInStock = numberInStock;
    }

    public String getDescription() {
        return description;
    }

    public double getNumberInStock() {
        return numberInStock;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", numberInStock=" + numberInStock +
                '}';
    }
}
