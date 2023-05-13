package gamestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import gamestore.InventoryManager;
import gamestore.ShoppingCart;

public class StoreFront {
    private List<Product> inventory;  // Declare private instance variable inventory of type List<Product>
    private ShoppingCart shoppingCart;  // Declare private instance variable shoppingCart of type ShoppingCart
    private Scanner scanner;  // Declare private instance variable scanner of type Scanner

    public StoreFront() {  // Constructor
        this.inventory = new ArrayList<>();  // Initialize inventory to a new ArrayList<Product>
        this.shoppingCart = new ShoppingCart();  // Initialize shoppingCart to a new ShoppingCart object
        this.scanner = new Scanner(System.in);  
        initialize();  // Call initialize method to add products to the inventory list
    }
    
    public void displayStoreFront() {  // Method to display the store front
        System.out.println("Welcome to the Arena Fighting game store!");
        System.out.println("-------------------------------------------------");
        System.out.println("Available products:");
        for (Product product : inventory) {  // Loop through each product in inventory
            System.out.println(product.getName() + " - $" + product.getPrice() + " - " + product.getQuantity() + " in stock");  // Print the name, price, and quantity of each product
        }
        System.out.println("-------------------------------------------------");
    }

    private void initialize() {  // Method to initialize the inventory list with products
        Product p1 = new Product("Armor", "Armor increases health", 7.99, 10);  // Create a new product object with name, description, price, and quantity as arguments
        Product p2 = new Product("Sword", "Sword is effective for close range combat", 39.99, 5);  // Create a new product object with name, description, price, and quantity as arguments
        Product p3 = new Product("Bow", "Bow is effective for long range combat", 49.99, 3);  // Create a new product object with name, description, price, and quantity as arguments
        inventory.add(p1);  
        inventory.add(p2); 
        inventory.add(p3);  
    }

    public void run() {  // Method to start the store program
        boolean continueShopping = true;
        while (continueShopping) {  // While loop to continue shopping until user wants to checkout
            System.out.println("Welcome to the Arena Fighting game store!");
            System.out.println("Current Inventory:");
            for (Product product : inventory) {  // Loop through each product in inventory
                System.out.println(product.getName() + " - $" + product.getPrice() + " - " + product.getQuantity() + " in stock");  // Print the name, price, and quantity of each product
            }
            System.out.print("Enter the name of the product you would like to purchase, or type 'checkout' to proceed to checkout: ");  // Prompt user to enter the name of the product or type 'checkout'
            String input = scanner.nextLine();  // Get user input as a string
            if (input.equalsIgnoreCase("checkout")) {  // If user input is 'checkout'
                checkout();  // Call checkout method to proceed to checkout and end shopping
                continueShopping = false;  // Set continueShopping flag to false to end while loop
            } else {
                boolean productFound = false;
                for (Product product : inventory) {  // Loop through the inventory to find the selected product
                    if (product.getName().equalsIgnoreCase(input)) {  // If the selected product is found
                        productFound = true;
                        System.out.println("Selected product: " + product.getName() + " - $" + product.getPrice());  // Print the selected product name and price
                        System.out.print("Enter quantity to purchase: ");  // Ask the user to enter the quantity of the product they want to buy
                        int quantity = scanner.nextInt();  // Read the user input for quantity
                        scanner.nextLine(); // consume the newline character
                        if (quantity <= 0) {  // If the quantity entered is less than or equal to 0, it's an invalid input
                            System.out.println("Invalid quantity.");
                        } else if (quantity > product.getQuantity()) {  // If the quantity entered is greater than the quantity available in inventory, it's an invalid input
                            System.out.println("Insufficient quantity in stock.");
                        } else {
                            shoppingCart.addToCart(product, quantity);  // If the quantity is valid, add the product and quantity to the shopping cart
                            System.out.println(quantity + " " + product.getName() + " added to cart.");  // Print a message indicating the product and quantity added to the shopping cart
                        }
                        break;  // Break out of the for loop since the selected product has been found and processed
                    }
                }
                if (!productFound) {  // If the selected product is not found in the inventory
                    System.out.println("Product not found.");  // Print a message indicating that the product is not found
                }
            }
        }
        }

            private void checkout() {  // The checkout method to process the purchase and update inventory
                System.out.println("Shopping Cart:");  // Print a message indicating the start of the checkout process
                shoppingCart.displayCart();  // Display the items in the shopping cart
                System.out.print("Enter 'confirm' to confirm your purchase, or 'cancel' to cancel: ");  // Ask the user to confirm or cancel the purchase
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("confirm")) {  // If the user confirms the purchase
                    System.out.println("Thank you for your purchase!");  // Print a message thanking the user for the purchase

                    // Update inventory and clear shopping cart
                    for (Product product : shoppingCart.getCart()) {  // Loop through the items in the shopping cart
                        int quantity = product.getQuantity();  // Get the quantity of the product in the shopping cart
                        InventoryManager inventoryManager = new InventoryManager();  // Create a new inventory manager instance
                        if (inventoryManager.updateInventory(product.getName(), -quantity)) {  // Update the inventory by subtracting the purchased quantity from the available quantity
                            System.out.println(quantity + " " + product.getName() + " purchased.");  // Print a message indicating the successful purchase of the product
                        } else {
                            System.out.println("Error: " + product.getName() + " could not be purchased.");  // Print an error message if the purchase of the product is unsuccessful
                        }
                    }
                    shoppingCart.emptyCart();  // Clear the shopping cart after the purchase is complete

            // Ask user if they want to continue shopping
            System.out.print("Would you like to continue shopping? (Y/N): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                displayStoreFront();
            } else {
                System.out.println("Thank you for shopping with us. Goodbye!");
                scanner.close();
            }
        } else if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Purchase canceled. Exiting store.");
            shoppingCart.emptyCart();
            System.exit(0); 
        } else {
            System.out.println("Invalid input. Returning to store front.");
            displayStoreFront();
        }
    }
    public static void main(String[] args) {
        StoreFront store = new StoreFront();
        store.displayStoreFront();
        store.run();
    }
}

