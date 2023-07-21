package gamestore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameStoreTest {
    private InventoryManager inventoryManager;
    private Product product;

    @BeforeEach
    public void setup() {
        inventoryManager = new InventoryManager();
    }

    @Test
    public void testGetInventory() {
        // Get the inventory list
        List<Product> inventory = inventoryManager.getInventory();
        // Assert that the inventory is not null
        Assertions.assertNotNull(inventory);
        // Assert that the inventory is not empty
        Assertions.assertFalse(inventory.isEmpty());
    }

    @Test
    public void testIsAvailableReturnsTrueWhenProductExistsWithSufficientQuantity() {
        // Get the inventory list
        List<Product> inventory = inventoryManager.getInventory();
        // Get the first product from the inventory
        Product product = inventory.get(0);
        // Check if the product is available with sufficient quantity
        boolean isAvailable = inventoryManager.isAvailable(product.getName(), product.getQuantity());
        // Assert that the product is available
        Assertions.assertTrue(isAvailable, "Expected product to be available with sufficient quantity");
    }

    @Test
    public void testIsAvailableReturnsFalseWhenProductDoesNotExist() {
        // Check if a non-existent product is available
        boolean isAvailable = inventoryManager.isAvailable("Nonexistent Product", 1);
        // Assert that the product is unavailable
        Assertions.assertFalse(isAvailable, "Expected product to be unavailable as it does not exist");
    }

    @Test
    public void testIsAvailableReturnsFalseWhenProductExistsWithInsufficientQuantity() {
        // Get the inventory list
        List<Product> inventory = inventoryManager.getInventory();
        // Get the first product from the inventory
        Product product = inventory.get(0);
        // Check if the product is available with insufficient quantity
        boolean isAvailable = inventoryManager.isAvailable(product.getName(), product.getQuantity() + 1);
        // Assert that the product is unavailable
        Assertions.assertFalse(isAvailable, "Expected product to be unavailable with insufficient quantity");
    }

    @Test
    public void testGetProductReturnsProductWhenExistingProductIsRequested() {
        // Get the inventory list
        List<Product> inventory = inventoryManager.getInventory();
        // Get the first product from the inventory
        Product product = inventory.get(0);
        // Retrieve the product by name
        Product retrievedProduct = inventoryManager.getProduct(product.getName());
        // Assert that the retrieved product is the same as the requested product
        Assertions.assertEquals(product, retrievedProduct, "Expected retrieved product to be the same as the requested product");
    }

    @Test
    public void testGetProductReturnsNullWhenNonexistentProductIsRequested() {
        // Retrieve a non-existent product
        Product retrievedProduct = inventoryManager.getProduct("Nonexistent Product");
        // Assert that the retrieved product is null
        Assertions.assertNull(retrievedProduct, "Expected retrieved product to be null for a nonexistent product");
    }

    @Test
    public void testGetProductNotFound() {
        // Get a non-existent product
        Product product = inventoryManager.getProduct("Non-existent Product");
        // Assert that the product is null
        Assertions.assertNull(product);
    }

    @Test
    public void testRemoveProduct() {
        // Create a sample product
        Product product = new Product("Test Product", "Sample description", 9.99, 10);
        // Add the product to the inventory
        inventoryManager.addProduct(product, 0);
        // Remove 5 quantity of the product
        inventoryManager.removeProduct(product, 5);
        // Verify that the quantity is reduced by 5
        Assertions.assertEquals(5, product.getQuantity());
    }

    @Test
    public void testAddProduct() {
        // Create a sample product
        Product product = new Product("Test Product", "Sample description", 9.99, 0);
        // Add 10 quantity of the product
        inventoryManager.addProduct(product, 10);
        // Verify that the quantity is increased to 10
        Assertions.assertEquals(10, product.getQuantity());
    }

    @Test
    public void testIsAvailableInsufficientQuantity() {
        // Check if a product is available with insufficient quantity
        boolean available = inventoryManager.isAvailable("Product 1", 10);
        // Assert that the product is unavailable
        Assertions.assertFalse(available);
    }

    @Test
    public void testUpdateInventory() {
        // Define a JSON string for updating inventory
        String jsonData = "[{\"name\":\"New Product\",\"quantity\":10}]";
        // Update the inventory using the JSON data
        inventoryManager.updateInventory(jsonData);
        // Get the updated product from the inventory
        Product newProduct = inventoryManager.getProduct("New Product");
        // Assert that the new product is not null and has a quantity of 10
        Assertions.assertNotNull(newProduct);
        Assertions.assertEquals(10, newProduct.getQuantity());
    }

    @Test
    public void testGetInventoryData() {
        // Get the inventory data as a string
        String inventoryData = inventoryManager.getInventoryData();
        // Assert that the inventory data is not null and not empty
        Assertions.assertNotNull(inventoryData);
        Assertions.assertFalse(inventoryData.isEmpty());
    }

    @BeforeEach
    void setUp() {
        // Create a new product object before each test
        product = new Product("Game", "Awesome game", 49.99, 10);
    }

    @Test
    void testGetName() {
        // Define the expected name
        String expectedName = "Game";
        // Get the actual name of the product
        String actualName = product.getName();
        // Assert that the actual name matches the expected name
        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    void testGetDescription() {
        // Define the expected description
        String expectedDescription = "Awesome game";
        // Get the actual description of the product
        String actualDescription = product.getDescription();
        // Assert that the actual description matches the expected description
        Assertions.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testGetPrice() {
        // Define the expected price
        double expectedPrice = 49.99;
        // Get the actual price of the product
        double actualPrice = product.getPrice();
        // Assert that the actual price matches the expected price
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testGetQuantity() {
        // Define the expected quantity
        int expectedQuantity = 10;
        // Get the actual quantity of the product
        int actualQuantity = product.getQuantity();
        // Assert that the actual quantity matches the expected quantity
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    void testSetQuantity() {
        // Define a new quantity
        int newQuantity = 5;
        // Set the new quantity for the product
        product.setQuantity(newQuantity);
        // Assert that the product's quantity is updated to the new value
        Assertions.assertEquals(newQuantity, product.getQuantity());
    }

    @Test
    void testGetDefense() {
        // Create an instance of Armor
        Armor armor = new Armor("Iron Armor", "Heavy iron armor", 50.0, 1, 10);
        // Test the getDefense() method
        int defense = armor.getDefense();
        // Assert that the returned defense value is correct
        Assertions.assertEquals(10, defense);
    }

    @Test
    void healthConstructor_ShouldSetFieldsCorrectly() {
        // Arrange
        String name = "Health Potion";
        String description = "Restores health";
        double price = 9.99;
        int quantity = 10;
        int healingAmount = 50;
        // Act
        Health health = new Health(name, description, price, quantity, healingAmount);
        // Assert
        Assertions.assertEquals(name, health.getName());
        Assertions.assertEquals(description, health.getDescription());
        Assertions.assertEquals(price, health.getPrice(), 0.001);
        Assertions.assertEquals(quantity, health.getQuantity());
        Assertions.assertEquals(healingAmount, health.getHealingAmount());
    }

    @Test
    void getHealingAmount_ShouldReturnCorrectValue() {
        // Arrange
        String name = "Health Potion";
        String description = "Restores health";
        double price = 9.99;
        int quantity = 10;
        int healingAmount = 50;
        Health health = new Health(name, description, price, quantity, healingAmount);
        // Act
        int actualHealingAmount = health.getHealingAmount();
        // Assert
        Assertions.assertEquals(healingAmount, actualHealingAmount);
    }

    @Test
    void testGetDamage() {
        // Arrange
        int expectedDamage = 50;
        Weapon weapon = new Weapon("Sword", "A powerful sword", 100.0, 1, expectedDamage);
        // Act
        int actualDamage = weapon.getDamage();
        // Assert
        Assertions.assertEquals(expectedDamage, actualDamage);
    }

    @Test
    void testCompareTo_SameName() {
        // Arrange
        Weapon weapon1 = new Weapon("Sword", "A powerful sword", 100.0, 1, 50);
        Weapon weapon2 = new Weapon("Sword", "A different sword", 150.0, 1, 75);
        // Act
        int result = weapon1.compareTo(weapon2);
        // Assert
        Assertions.assertEquals(0, result);
    }

    @Test
    void testCompareTo_LesserName() {
        // Arrange
        Weapon weapon1 = new Weapon("Axe", "A mighty axe", 120.0, 1, 80);
        Weapon weapon2 = new Weapon("Sword", "A powerful sword", 100.0, 1, 50);
        // Act
        int result = weapon1.compareTo(weapon2);
        // Assert
        Assertions.assertTrue(result < 0);
    }

    @Test
    void testCompareTo_GreaterName() {
        // Arrange
        Weapon weapon1 = new Weapon("Sword", "A powerful sword", 100.0, 1, 50);
        Weapon weapon2 = new Weapon("Axe", "A mighty axe", 120.0, 1, 80);
        // Act
        int result = weapon1.compareTo(weapon2);
        // Assert
        Assertions.assertTrue(result > 0);
    }
}








