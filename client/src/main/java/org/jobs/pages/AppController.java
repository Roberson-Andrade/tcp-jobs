package org.jobs.pages;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import org.jobs.tcp.Client;

import java.io.IOException;

public class AppController {
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Client.request(new JSONObject("{ \"operacao\": \"loginCandidato\" }"));
    }

    @FXML
    protected void onSubmit() throws IOException {
        JSONObject data = new JSONObject("{ \"operacao\": \"loginCandidato\" }");

        data.put("email", email.getText());
        data.put("senha", password.getText());
        data.put("nome", "Roberson");

        Client.request(data);
    }
}