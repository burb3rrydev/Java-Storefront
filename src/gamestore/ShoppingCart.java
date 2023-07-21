package gamestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class ShoppingCart<E> {
    private List<E> cartItems; // Declare a private list to store cart items of generic type parameter E

    public ShoppingCart() {
        cartItems = new ArrayList<>(); // Initialize the cartItems list as an empty ArrayList
        							   // E is a placeholder for the actual type that will be provided when creating an instance of the ShoppingCart class
    }

    public List<E> getCartItems() {
        return cartItems; // Return the cartItems list
    }

    public int getQuantity(E product) {
        // Initialize a variable to hold the quantity of the product in the cart
        // Use the Collections class and frequency algorithm to count the occurrences of the product
        // in the `cartItems` list. This method returns the number of times the specified
        // object (product) appears in the list.
        int quantity = Collections.frequency(cartItems, product);
        return quantity;
    }

    public void addProduct(E product, int quantity) {
        for (int i = 0; i < quantity; i++) { // Repeat the following block 'quantity' times
            cartItems.add(product); // Add the given product to the cartItems list
        }
    }

    public void removeProduct(E product, int quantity) {
        for (int i = 0; i < quantity; i++) { // Repeat the following block 'quantity' times
            cartItems.remove(product); // Remove the given product from the cartItems list
        }
    }

    public void clearCart() {
        cartItems.clear(); // Clear the cartItems list, removing all items from the cart
    }
}






