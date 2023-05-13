package gamestore;

//Defines a public class named "Product"
public class Product {
 
 // Declares private instance variables for the product's name, description, price, quantity, and cart quantity
 private String name;
 private String description;
 private double price;
 private int quantity;
 private int cartQuantity;

 // Defines a constructor method that takes in the product's name, description, price, and quantity as parameters
 public Product(String name, String description, double price, int quantity) {
     // Assigns the name, description, price, and quantity parameters to the corresponding instance variables
     this.name = name;
     this.description = description;
     this.price = price;
     this.quantity = quantity;
     // Sets the initial cart quantity to 0
     this.cartQuantity = 0;
 }

 // Defines a getter method for the product's name
 public String getName() {
     return name;
 }

 // Defines a setter method for the product's name
 public void setName(String name) {
     this.name = name;
 }

 // Defines a getter method for the product's description
 public String getDescription() {
     return description;
 }

 // Defines a setter method for the product's description
 public void setDescription(String description) {
     this.description = description;
 }

 // Defines a getter method for the product's price
 public double getPrice() {
     return price;
 }

 // Defines a setter method for the product's price
 public void setPrice(double price) {
     this.price = price;
 }

 // Defines a getter method for the product's quantity
 public int getQuantity() {
     return quantity;
 }

 // Defines a setter method for the product's quantity
 public void setQuantity(int quantity) {
     this.quantity = quantity;
 }
 
 // Defines a getter method for the product's cart quantity
 public int getCartQuantity() {
     return cartQuantity;
 }

 // Defines a setter method for the product's cart quantity
 public void setCartQuantity(int cartQuantity) {
     this.cartQuantity = cartQuantity;
 }

}

