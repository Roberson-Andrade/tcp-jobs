package org.jobs.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONObject;
import org.jobs.tcp.Client;

import java.io.IOException;

public class AppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Client.request(new JSONObject("{ \"operacao\": \"loginCandidato\" }"));
    }

    @FXML
    protected void onChangeEmail() throws IOException {
        Client.request(new JSONObject("{ \"operacao\": \"loginCandidato\" }"));
    }
}