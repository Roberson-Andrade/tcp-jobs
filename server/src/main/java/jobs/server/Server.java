package jobs.server;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server extends Thread {
    private final Routes routes;
    protected Socket clientSocket;

    private Server(Socket clientSoc) throws SQLException {
        clientSocket = clientSoc;
        this.routes = Routes.getInstance();
        start();
    }

    public static void listen(Integer port) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Connection Socket Created");
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new Server(serverSocket.accept());
                }
            } catch (IOException | SQLException e) {
                System.err.println(e);
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
            System.exit(1);
        } finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 10008.");
                System.exit(1);
            }
        }
    }

    public void run() {
        System.out.println("New Communication Thread Started");

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage = "";
            boolean isLogout = false;

            do {
                try {
                    clientMessage = in.readLine();
                    System.out.println("Recebido: " + clientMessage);

                    JSONObject response = this.routes.route(clientMessage);

                    String responseStr = response.toString();
                    System.out.println("Enviado: " + responseStr);

                    out.println(responseStr);

                    isLogout = response.getString("operacao").equals("logout");
                } catch (Exception error) {
                    JSONObject errorResponse = new JSONObject();
                    errorResponse.put("mensagem", error.getMessage());
                    errorResponse.put("status", 400);
                    System.out.println("Enviado: " + errorResponse);
                    out.println(errorResponse);
                }
            } while (clientMessage != null && !isLogout);

            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close client socket: " + e.getMessage());
            }
        }
    }
}