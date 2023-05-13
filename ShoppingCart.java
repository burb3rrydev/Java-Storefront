// Import the ArrayList and List classes from the Java library
package gamestore;
import java.util.ArrayList;
import java.util.List;

// Define the ShoppingCart class
public class ShoppingCart {
    // Declare a private List instance variable to store the products in the cart
    private List<Product> cart;

    // Define a constructor method for the ShoppingCart class
    public ShoppingCart() {
        // Create a new ArrayList instance and assign it to the cart variable
        this.cart = new ArrayList<>();
    }

    // Define a method to add a Product object to the cart with a specified quantity
    public void addToCart(Product product, int quantity) {
        // Check if the quantity requested is greater than the quantity available in stock
        if (product.getQuantity() < quantity) {
            // If it is, print a message to the console indicating that there is not enough stock
            System.out.println("Insufficient quantity in stock. Please select a lower quantity or choose a different product.");
        } else {
            // If there is enough stock, add the product to the cart with the specified quantity
            cart.add(new Product(product.getName(), product.getDescription(), product.getPrice(), quantity));
            // Print a message to the console indicating that the product was added to the cart
            System.out.println(quantity + " " + product.getName() + " added to cart.");
            // Update the quantity of the product in stock
            product.setQuantity(product.getQuantity() - quantity);
        }
    }

    // Define a method to remove a Product object from the cart with a specified quantity
    public void removeFromCart(Product product, int quantity) {
        // Iterate over each product in the cart
        for (Product p : cart) {
            // If the product in the cart has the same name as the product being removed
            if (p.getName().equals(product.getName())) {
                // Remove the product from the cart
                cart.remove(p);
                // Update the quantity of the product in stock
                product.setQuantity(product.getQuantity() + quantity);
                // Print a message to the console indicating that the product was removed from the cart
                System.out.println(quantity + " " + product.getName() + " removed from cart.");
                // Exit the loop
                break;
            }
        }
    }

    // Define a method to display the contents of the cart
    public void displayCart() {
        // If the cart is empty, print a message to the console indicating that it is empty
        if (cart.size() == 0) {
            System.out.println("Your cart is empty.");
        } else {
            // Iterate over each product in the cart
            for (Product product : cart) {
                // Print the name, price, and quantity of each product in the cart to the console
                System.out.println(product.getName() + " - $" + product.getPrice() + " - " + product.getQuantity() + " in cart");
            }
        }
    }

    // Define a method to return the list of products in the cart
    public List<Product> getCart() {
        return cart;
    }

    // Define a method to empty the cart and return the products to stock
    public void emptyCart() {
        // Iterate over each product in the cart
        for (Product product : cart) {
            // Add the quantity of the product in the cart back to the stock quantity
            product.setQuantity(product.getQuantity() + product.getCartQuantity());
            // Reset the cartQuantity to zero
            product.setCartQuantity(0);
        }
        // Clear the cart
        cart.clear();
    }
}

