package gamestore;

public class Armor extends Product {
    private String material;  // Declare a private instance variable for the armor's material

    public Armor(String name, String description, double price, int quantity, String material) {
        super(name, description, price, quantity);  // Call the constructor of the parent class (Product) with the provided arguments
        this.material = material;  // Assign the provided material to the instance variable
    }

    public String getMaterial() {  // Define a getter method to retrieve the material of the armor
        return material;  // Return the value of the material instance variable
    }

    public void setMaterial(String material) {  // Define a setter method to set the material of the armor
        this.material = material;  // Assign the provided material to the instance variable
    }
}

