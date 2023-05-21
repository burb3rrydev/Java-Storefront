package gamestore;

public class Health extends Product {
    private int healingPoints;

    // Constructor for the Health class that takes in the name, description, price, quantity, and healingPoints as parameters
    public Health(String name, String description, double price, int quantity, int healingPoints) {
        // Calls the constructor of the superclass (Product) with the name, description, price, and quantity parameters
        super(name, description, price, quantity);
        // Assigns the healingPoints parameter to the private instance variable of the Health class
        this.healingPoints = healingPoints;
    }

    // Getter method to retrieve the healingPoints value
    public int getHealingPoints() {
        return healingPoints;
    }

    // Setter method to set the healingPoints value
    public void setHealingPoints(int healingPoints) {
        this.healingPoints = healingPoints;
    }
}


