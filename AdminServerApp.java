package gamestore;

public class AdminServerApp {
    public static void main(String[] args) {
        AdminServiceServer server = new AdminServiceServer();
        AdminServiceClient client = new AdminServiceClient();

        // Start the server
        Thread serverThread = new Thread(() -> {
            server.start();
        });
        serverThread.start();

        // Wait for the server to start 
        try {
            Thread.sleep(1000); // Sleep for 1 second to allow the server to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Run the client
        client.sendCommands();

        // Stop the server
        server.stop(); // Call the stop method to shut down the server
    }
}



