package gamestore;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    // Initialize the inventory with a list of Product objects
    private List<Product> inventory;

    // Constructor: initialize the inventory and add initial products to it
    public InventoryManager() {
        this.inventory = new ArrayList<>();
        initialize();
    }

    // Helper method to add initial products to the inventory
    private void initialize() {
        Product p1 = new Product("Armor", "Armor increases health", 7.99, 10);
        Product p2 = new Product("Sword", "Sword is effective for close range combat", 39.99, 5);
        Product p3 = new Product("Bow", "Bow is effective for long range combat", 49.99, 3);
        inventory.add(p1);
        inventory.add(p2);
        inventory.add(p3);
    }

    // Getter method to retrieve the inventory
    public List<Product> getInventory() {
        return inventory;
    }


    // Method to update the inventory given a product name and the quantity to add or remove
    public boolean updateInventory(String productName, int quantity) {
        // Loop through each product in the inventory
        for (Product product : inventory) {
            // If the name of the current product matches the given product name
            if (product.getName().equals(productName)) {
                // If the quantity of the current product is greater than or equal to the given quantity
                if (product.getQuantity() >= quantity) {
                    // Update the quantity of the current product by the given quantity
                    product.setQuantity(product.getQuantity() + quantity);
                    // Return true to indicate that the inventory was successfully updated
                    return true;
                } else {
                    // Return false to indicate that the inventory was not updated because there was not enough of the product in stock
                    return false;
                }
            }
        }
        // Return false to indicate that the inventory was not updated because the product was not found in the inventory
        return false;
    }
}



