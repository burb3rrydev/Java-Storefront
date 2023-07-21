// AdminServiceClient.java
package gamestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminServiceClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("U"); // Send the 'U' command to update the inventory

            // Provide the JSON payload to update the inventory
            String jsonPayload = "[{\"name\": \"Health Pack\", \"description\": \"25% Health Regeneration\", \"price\": 24.99, \"quantity\": 100}, {\"name\": \"Body Armor\", \"description\": \"Protective body armor\", \"price\": 29.99, \"quantity\": 25}]";
            out.println(jsonPayload);

            String response = in.readLine(); // Receive the server's response after updating the inventory
            System.out.println("Server response: " + response);

            out.println("R"); // Send the 'R' command to request the inventory

            String jsonResponse = in.readLine(); // Receive the inventory in JSON format
            System.out.println("Inventory received:\n" + jsonResponse);
        } catch (IOException e) {
            System.out.println("Error occurred while running the client: " + e.getMessage());
        }
    }
}










