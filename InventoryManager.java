package gamestore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Product> inventory;

    public InventoryManager() {
        initializeInventory();
    }

    private void initializeInventory() {
        try {
            File jsonFile = new File("inventory.json"); // Specify the path to the JSON file

            ObjectMapper objectMapper = new ObjectMapper(); // Create an instance of ObjectMapper for JSON parsing
            inventory = objectMapper.readValue(jsonFile, new TypeReference<List<Product>>() {}); // Read the JSON data and map it to a List of Product objects
        } catch (IOException e) {
            System.out.println("Error occurred while reading inventory data from JSON file: " + e.getMessage());
            inventory = new ArrayList<>(); // Initialize an empty inventory in case of an exception
        }
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public Product getProduct(String productName) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null; // If no matching product is found, return null
    }

    public boolean isAvailable(String productName, int quantity) {
        Product product = getProduct(productName); // Get the product by name
        return product != null && product.getQuantity() >= quantity; // Check if the product exists and if its quantity is sufficient
    }

    public void removeProduct(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity); // Reduce the quantity of the specified product
    }

    public void addProduct(Product product, int quantity) {
        product.setQuantity(product.getQuantity() + quantity); // Increase the quantity of the specified product
    }

    public void updateInventory(String jsonData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Product> newProducts = objectMapper.readValue(jsonData, new TypeReference<List<Product>>() {});
            for (Product newProduct : newProducts) {
                Product existingProduct = getProduct(newProduct.getName());
                if (existingProduct != null) {
                    // Product already exists in inventory, update the quantity
                    existingProduct.setQuantity(newProduct.getQuantity());
                } else {
                    // Product doesn't exist in inventory, add it
                    inventory.add(newProduct);
                }
            }

            // Save the updated inventory to the JSON file
            objectMapper.writeValue(new FileWriter("inventory.json"), inventory);
        } catch (IOException e) {
            System.out.println("Error occurred while updating inventory: " + e.getMessage());
        }
    }

    public String getInventoryData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(inventory);
        } catch (IOException e) {
            System.out.println("Error occurred while retrieving inventory data: " + e.getMessage());
            return "";
        }
    }
}












