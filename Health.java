package gamestore;  // Declares the package name for the class

class Health extends Product {  // Defines a class named "Health" that extends the "Product" class
    private int healingAmount;  // Declares a private integer variable named "healingAmount"

    public Health(String name, String description, double price, int quantity, int healingAmount) {
        // Constructor for the Health class that takes parameters for name, description, price, quantity, and healingAmount
        super(name, description, price, quantity);  // Calls the constructor of the superclass "Product" with the provided parameters
        this.healingAmount = healingAmount;  // Assigns the value of healingAmount passed to the constructor to the instance variable
    }

    public int getHealingAmount() {
        // Getter method to retrieve the value of the healingAmount variable
        return healingAmount;  // Returns the value of healingAmount
    }
}



