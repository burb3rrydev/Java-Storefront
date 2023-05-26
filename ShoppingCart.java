// Import the ArrayList and List classes from the Java library
package gamestore;
import java.util.ArrayList;
import java.util.List;

// Define the ShoppingCart class
class ShoppingCart {
    private List<Product> cartItems; // Declare a private List variable called cartItems

    public ShoppingCart() {
        cartItems = new ArrayList<>(); // Initialize cartItems as a new ArrayList
    }

    public List<Product> getCartItems() { // Define a method to get the cart items
        return cartItems; // Return the cartItems list
    }

    public int getQuantity(Product product) { // Define a method to get the quantity of a specific product in the cart
        int quantity = 0; // Initialize a variable to store the quantity
        for (Product item : cartItems) { // Iterate over each item in the cartItems list
            if (item.getName().equals(product.getName())) { // Check if the item's name matches the given product's name
                quantity++; // Increment the quantity if there is a match
            }
        }
        return quantity; // Return the quantity of the product
    }

    public void addProduct(Product product, int quantity) { // Define a method to add a product to the cart
        for (int i = 0; i < quantity; i++) { // Repeat the following steps 'quantity' times
            cartItems.add(product); // Add the product to the cartItems list
        }
    }

    public void removeProduct(Product product, int quantity) { // Define a method to remove a product from the cart
        for (int i = 0; i < quantity; i++) { // Repeat the following steps 'quantity' times
            cartItems.remove(product); // Remove the product from the cartItems list
        }
    }

    public void clearCart() { // Define a method to clear the entire cart
        cartItems.clear(); // Remove all items from the cartItems list
    }
}



