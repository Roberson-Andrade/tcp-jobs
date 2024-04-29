package org.jobs.tcp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static String ip = "127.0.0.1";
    static Integer port = 22222;
    static String token;

    public static JSONObject request(JSONObject input) throws IOException {
        String serverHostname = ip;
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(serverHostname, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Endereço " + serverHostname + " não encontrado");
            throw new IOException(e);
        } catch (IOException e) {
            System.err.println("Não foi possível conectar ao endereço: " + ip + ":" + port);
            throw new IOException(e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String inputStr = input.toString();
        System.out.println("Enviado: " + inputStr);

        // sending to server
        out.println(inputStr);

        String result = in.readLine();

        System.out.println("Recebido: " + result);

        if (result == null) {
            throw new IOException("Erro interno");
        }

        out.close();
        in.close();
        echoSocket.close();

        return new JSONObject(result);
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

