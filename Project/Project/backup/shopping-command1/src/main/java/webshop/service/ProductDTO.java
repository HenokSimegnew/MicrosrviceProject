package webshop.service;

public class ProductDTO {
    private String productNumber;
    private String name;
    private double price;
    private String description;
    private int numberInStock;

    public ProductDTO(String name, String productNumber, String description, double price, int numberInStock) {
        this.name = name;
        this.productNumber = productNumber;
        this.price = price;
        this.description = description;
        this.numberInStock = numberInStock;
    }

    public ProductDTO() {
    }

    public String getProductNumber() {
        return productNumber;
    }

    public double getPrice() {
        return price;
    }


    public String getDescription() {
        return description;
    }

    public int getNumberInStock() {
        return numberInStock;
    }


    public String getName() {
        return name;
    }

}
