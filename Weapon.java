package gamestore;

public class Weapon extends Product {
    private String type;  // Declare a private instance variable to store the type of weapon

    public Weapon(String name, String description, double price, int quantity, String type) {
        // Constructor method for Weapon class that takes in the name, description, price, quantity, and type as parameters
        super(name, description, price, quantity);  // Call the superclass (Product) constructor to initialize inherited attributes
        this.type = type;  // Assign the type parameter to the type instance variable
    }

    public String getType() {
        return type;  // Return the value of the type instance variable
    }

    public void setType(String type) {
        this.type = type;  // Set the value of the type instance variable to the value of the type parameter
    }
}