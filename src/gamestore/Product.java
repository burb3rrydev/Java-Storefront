package gamestore;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Defines a public class named "Product"
class Product {
    // Private instance variables
    private String name;          // Stores the name of the product
    private String description;   // Stores the description of the product
    private double price;         // Stores the price of the product
    int quantity;                 // Stores the quantity of the product (default access modifier)

    // Constructor for the Product class
    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") double price,
                   @JsonProperty("quantity") int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    

    // Getter method for retrieving the name of the product
    public String getName() {
        return name;
    }

    // Getter method for retrieving the description of the product
    public String getDescription() {
        return description;
    }

    // Getter method for retrieving the price of the product
    public double getPrice() {
        return price;
    }

    // Getter method for retrieving the quantity of the product
    public int getQuantity() {
        return quantity;
    }
      
    // Setter method for retrieving the quantity of the product
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

