package gamestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminServiceServer {
    private static final int PORT = 8080;
    private static InventoryManager inventoryManager;

    public static void start() {
        inventoryManager = new InventoryManager(); // Create an instance of the InventoryManager
        
        // Creates a ServerSocket instance to listen on the specified PORT.
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("AdminServer started. Waiting for connections...");

            while (shouldShutdown(null)) { // Loop until the server should shut down
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Create output stream for sending data to the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Create input stream for receiving data from the client

                String command = in.readLine(); // Read the command sent by the client

                if (command != null) {
                	// Checks if a command was received from the client.
                    if (command.equals("U")) {
                        // Process the 'U' command to update the inventory
                        String jsonPayload = in.readLine(); // Reads the JSON payload sent by the client
                        System.out.println("Received JSON payload:\n" + jsonPayload);
                        
                        // update inventory
                        inventoryManager.updateInventory(jsonPayload);

                        System.out.println("Inventory updated successfully.");
                        out.println("Inventory updated successfully.");

                        // Print the updated inventory to the console
                        String inventoryData = inventoryManager.getInventoryData();
                        System.out.println("Current Inventory:\n" + inventoryData);
                    } else if (command.equals("R")) {
                        // Process the 'R' command to return the inventory
                        String jsonResponse = inventoryManager.getInventoryData();

                        System.out.println("Sending inventory:\n" + jsonResponse);
                        out.println(jsonResponse);
                    }
                }

                out.close();
                in.close();
                clientSocket.close();

                // Check if the server should shut down
                if (shouldShutdown(command)) {
                    System.out.println("Shutting down the server...");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while running the server: " + e.getMessage());
        }
    }
    
    public static void stop() {
        try {
            // Connect to the server using a socket
            try (Socket socket = new Socket("localhost", PORT)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a special command to indicate server shutdown
                out.println("SHUTDOWN");

                // Close the socket
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while stopping the server: " + e.getMessage());
        }
    }
    
    

    private static boolean shouldShutdown(String command) {
        return command != null && (command.equals("U") || command.equals("R"));
        // This method takes a command as a parameter, which represents the command received from the client.
        // If the command is not null and is either "U" (update inventory) or "R" (return inventory), the server should shut down.
        // It returns true to indicate that the server should shut down, and false to indicate that the server should continue running.
    }
}









