package gamestore; // Declaring the package name where the class resides

class Armor extends Product { // Defining a class named "Armor" that extends the "Product" class
    private int defense; // Declaring a private integer variable "defense"

    public Armor(String name, String description, double price, int quantity, int defense) {
        // Constructor for the Armor class that takes parameters: name, description, price, quantity, and defense
        super(name, description, price, quantity); // Calling the constructor of the superclass "Product" with the specified parameters
        this.defense = defense; // Assigning the value of the "defense" parameter to the "defense" instance variable of the Armor class
    }

    public int getDefense() {
        return defense; // Returning the value of the "defense" instance variable
    }
}


