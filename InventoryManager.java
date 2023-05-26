package gamestore;

import java.util.ArrayList;
import java.util.List;

class InventoryManager {
    private List<Product> inventory; // Declaration of private list variable to store products in inventory

    public InventoryManager() {
        initializeInventory(); // Constructor that initializes the inventory by calling the initializeInventory() method
    }

    private void initializeInventory() {
        inventory = new ArrayList<>(); // Create a new ArrayList to store products in the inventory
        inventory.add(new Weapon("Sword", "A powerful sword", 49.99, 5, 20)); // Add a new Weapon object to the inventory list
        inventory.add(new Weapon("Axe", "A mighty axe", 59.99, 3, 30)); // Add a new Weapon object to the inventory list
        inventory.add(new Armor("Helmet", "Protective headgear", 29.99, 10, 15)); // Add a new Armor object to the inventory list
        inventory.add(new Armor("Shield", "Sturdy shield", 39.99, 8, 25)); // Add a new Armor object to the inventory list
        inventory.add(new Health("Potion", "Restores health", 9.99, 20, 50)); // Add a new Health object to the inventory list
    }

    public List<Product> getInventory() {
        return inventory; // Return the inventory list
    }

    public Product getProduct(String productName) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product; // Return the product if its name matches the given productName
            }
        }
        return null; // Return null if the product is not found in the inventory
    }

    public boolean isAvailable(String productName, int quantity) {
        Product product = getProduct(productName); // Get the product with the given productName
        return product != null && product.getQuantity() >= quantity; // Return true if the product exists and its quantity is greater than or equal to the given quantity
    }

    public void removeProduct(Product product, int quantity) {
        product.quantity -= quantity; // Decrease the quantity of the given product by the specified quantity
    }

    public void addProduct(Product product, int quantity) {
        product.quantity += quantity; // Increase the quantity of the given product by the specified quantity
    }
}






