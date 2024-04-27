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
    static Integer port = 10008;

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
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + serverHostname);
            System.exit(1);
        }

        String inputStr = input.toString();
        System.out.println("Enviado: " + inputStr);

        // sending to server
        out.println(inputStr);

        String result = in.readLine();

        System.out.println("Recebido: " + result);

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
}

