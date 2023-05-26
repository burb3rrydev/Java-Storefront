package gamestore;

// Importing necessary dependencies or classes

class Weapon extends Product implements Comparable<Weapon> {
    // Define a class named 'Weapon' that extends the 'Product' class and implements the 'Comparable' interface with type 'Weapon'
    
    private int damage;
    // Declare a private integer variable 'damage' to store the weapon's damage value

    public Weapon(String name, String description, double price, int quantity, int damage) {
        // Constructor for the 'Weapon' class that takes parameters: name, description, price, quantity, and damage
        super(name, description, price, quantity);
        // Call the constructor of the superclass 'Product' with the provided parameters to initialize the inherited fields
        this.damage = damage;
        // Assign the provided 'damage' value to the 'damage' variable of the current object
    }

    public int getDamage() {
        // Define a method named 'getDamage' that returns the weapon's damage value
        return damage;
    }

    @Override
    public int compareTo(Weapon other) {
        // Override the 'compareTo' method from the 'Comparable' interface to compare two Weapon objects
        return this.getName().compareToIgnoreCase(other.getName());
        // Compare the names of the current Weapon object and the 'other' Weapon object in a case-insensitive manner
        // Return a negative value if the current object comes before the 'other' object in lexicographic order
        // Return a positive value if the current object comes after the 'other' object in lexicographic order
        // Return zero if the names are equal
    }
}

