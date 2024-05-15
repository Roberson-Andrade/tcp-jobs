package org.jobs.tcp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jobs.app.App;
import org.jobs.pages.ErrorController;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    static Client instance = null;

    static String ip = "127.0.0.1";
    static Integer port = 22222;

    static String token;

    static int[] errorsCode = {400, 401, 403, 404, 500};

    Socket echoSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }

        return instance;
    }

    public void init() {
        if (echoSocket != null) {
            return;
        }

        try {
            echoSocket = new Socket(ip, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void disconnect() {
        try {
            out.close();
            in.close();
            echoSocket.close();

            out = null;
            in = null;
            echoSocket = null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public JSONObject request(JSONObject input) throws IOException {
        this.init();

        try {
            String inputStr = input.toString();
            System.out.println("Enviado: " + inputStr);

            // sending to server
            out.println(inputStr);

            String result = in.readLine();

            System.out.println("Recebido: " + result);

            if (result == null) {
                Client.showError("Ocorreu um erro durante a comunicação com servidor.");
                return null;
            }

            var response = new JSONObject(result);

            if (Client.isErrorResponse(response)) {
                Client.showError(response.getString("mensagem"));
                return null;
            }

            if (input.getString("operacao").equals("logout")) {
                this.disconnect();
            }

            return response;
        } catch (JSONException error) {
            Client.showError("Erro interno");
            return null;
        }
    }

    public static void  showError(String errorMessage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("error.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            var errorController = (ErrorController) fxmlLoader.getController();
            errorController.setErrorMessage(errorMessage);

            stage.setTitle("Erro");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ignored) {
        }
    }

    public static boolean isErrorResponse(JSONObject input) {
        boolean result = false;

        try {
            int status = input.getInt("status");

            for (int j : errorsCode) {
                if (j == status) {
                    return true;
                }
            }
        } catch (Exception error) {
            System.err.println(error.getMessage());
            return true;
        }

        return result;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Client.ip = ip;
    }

    public static Integer getPort() {
        return port;
    }

    public static void setPort(Integer port) {
        Client.port = port;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Client.token = token;
    }
}

