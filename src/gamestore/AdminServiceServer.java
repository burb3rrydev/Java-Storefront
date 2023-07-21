// AdminServiceServer.java
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

    public static void main(String[] args) {
        inventoryManager = new InventoryManager(); // Create an instance of the InventoryManager

        // Creates a ServerSocket instance to listen on the specified PORT.
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("AdminServer started. Waiting for connections...");

            while (true) { // Loop indefinitely to accept multiple client connections
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread clientThread = new ClientHandler(clientSocket); // Create a new thread to handle the client
                clientThread.start(); // Start the client thread
            }
        } catch (IOException e) {
            System.out.println("Error occurred while running the server: " + e.getMessage());
        }
    }

    public static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                String command = in.readLine(); // Read the command sent by the client

                if (command != null) {
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
            } catch (IOException e) {
                System.out.println("Error occurred while handling client request: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close(); // Close the client socket
                } catch (IOException e) {
                    System.out.println("Error occurred while closing client socket: " + e.getMessage());
                }
            }
        }
    }
}










