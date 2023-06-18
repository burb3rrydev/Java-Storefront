package gamestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminServiceClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public void sendCommands() {
    	//  Creates a socket connection to the specified SERVER_ADDRESS and SERVER_PORT.
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
        	// Creates a PrintWriter instance to send data to the server through the socket's output stream.
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
            // Creates a BufferedReader instance to receive data from the server through the socket's input stream.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

            // Send the 'U' command to update the inventory
            out.println("U");

            // Provide the JSON payload to update the inventory
            String jsonPayload = "[{\"name\": \"Health Pack\", \"description\": \"25% Health Regeneration\", \"price\": 24.99, \"quantity\": 100}, {\"name\": \"Body Armor\", \"description\": \"Protective body armor\", \"price\": 29.99, \"quantity\": 25}]";
            out.println(jsonPayload);

            // Receive the server's response after updating the inventory
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Send the 'R' command to request the inventory
            out.println("R");

            // Receive the inventory in JSON format
            String jsonResponse = in.readLine();
            System.out.println("Inventory received:\n" + jsonResponse);

            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println("Error occurred while running the client: " + e.getMessage());
        }
    }
}








