package gamestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreFront {
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;
    private Scanner scanner;

    public StoreFront() {
        inventoryManager = new InventoryManager(); // Create an instance of the InventoryManager
        shoppingCart = new ShoppingCart(); // Create an instance of the ShoppingCart
        scanner = new Scanner(System.in); // Create a Scanner object for user input
    }

    public void startShopping() {
        System.out.println("Welcome to the Store Front!");

        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Products available:");

            for (Product product : inventoryManager.getInventory()) {
                // Print information about each product in the inventory
                System.out.println(product.getName() + " - " + product.getDescription() + " - $" + product.getPrice() + " - Quantity: " + product.getQuantity());
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Options:");
            System.out.println("1. Add a product to the cart");
            System.out.println("2. Remove a product from the cart");
            System.out.println("3. View cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit store");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read the user's choice

            switch (choice) {
                case 1:
                    addToCart(); // Call the addToCart() method
                    break;
                case 2:
                    removeFromCart(); // Call the removeFromCart() method
                    break;
                case 3:
                    viewCart(); // Call the viewCart() method
                    break;
                case 4:
                    checkout(); // Call the checkout() method
                    break;
                case 5:
                    exitStore(); // Call the exitStore() method
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addToCart() {
        System.out.print("Enter the name of the product: ");
        String productName = scanner.next(); // Read the name of the product from the user

        if (productName.equalsIgnoreCase("cancel")) {
            cancelPurchase(); // Call the cancelPurchase() method if the user wants to cancel
            return;
        }

        Product product = inventoryManager.getProduct(productName); // Get the Product object from the InventoryManager

        if (product == null) {
            System.out.println("Product not found. Please try again.");
            return;
        }

        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt(); // Read the quantity from the user

        if (!inventoryManager.isAvailable(productName, quantity)) {
            System.out.println("Insufficient quantity. Please try again.");
            return;
        }

        inventoryManager.removeProduct(product, quantity); // Remove the product from the inventory
        shoppingCart.addProduct(product, quantity); // Add the product to the shopping cart

        System.out.println(quantity + " " + productName + "(s) added to the cart.");
    }

    private void removeFromCart() {
        System.out.print("Enter the name of the product: ");
        String productName = scanner.next(); // Read the name of the product from the user

        if (productName.equalsIgnoreCase("cancel")) {
            cancelPurchase(); // Call the cancelPurchase() method if the user wants to cancel
            return;
        }

        Product product = inventoryManager.getProduct(productName); // Get the Product object from the InventoryManager

        if (product == null) {
            System.out.println("Product not found. Please try again.");
            return;
        }

        int cartQuantity = shoppingCart.getQuantity(product); // Get the quantity of the product in the cart

        if (cartQuantity == 0) {
            System.out.println("Product not found in the cart. Please try again.");
            return;
        }

        System.out.print("Enter the quantity to remove: ");
        int quantity = scanner.nextInt(); // Read the quantity to remove from the user

        if (quantity > cartQuantity) {
            System.out.println("Invalid quantity. Please try again.");
            return;
        }

        inventoryManager.addProduct(product, quantity); // Add the product back to the inventory
        shoppingCart.removeProduct(product, quantity); // Remove the product from the cart

        System.out.println(quantity + " " + productName + "(s) removed from the cart.");
    }

    private void viewCart() {
        System.out.println("--------------------------------------------------");
        System.out.println("Contents of the cart:");

        for (Product product : shoppingCart.getCartItems()) {
            // Print information about each product in the cart
            System.out.println(product.getName() + " - " + product.getDescription() + " - $" + product.getPrice() + " - Quantity: " + shoppingCart.getQuantity(product));
        }

        System.out.println("--------------------------------------------------");
    }

    private void checkout() {
        System.out.println("--------------------------------------------------");
        System.out.println("Checkout");

        viewCart(); // Call the viewCart() method to display the cart items

        System.out.print("Type 'confirm' to proceed with the purchase or 'cancel' to cancel the purchase: ");
        String input = scanner.next(); // Read the user's input

        if (input.equalsIgnoreCase("confirm")) {
            System.out.println("Purchase confirmed. Thank you for shopping with us!");
            shoppingCart.clearCart(); // Clear the shopping cart after the purchase is confirmed
        } else if (input.equalsIgnoreCase("cancel")) {
            cancelPurchase(); // Call the cancelPurchase() method if the user wants to cancel
        } else {
            System.out.println("Invalid input. Purchase canceled.");
        }
    }

    private void cancelPurchase() {
        System.out.println("--------------------------------------------------");
        System.out.println("Purchase canceled. Returning products to inventory.");
        shoppingCart.clearCart(); // Clear the shopping cart when the purchase is canceled
    }

    private void exitStore() {
        System.out.println("Thank you for visiting the Store Front. Goodbye!");
    }

    public static void main(String[] args) {
        StoreFront storeFront = new StoreFront();
        storeFront.startShopping(); // Start the shopping process
    }
}




