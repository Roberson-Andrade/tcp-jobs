package org.jobs.pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import org.jobs.tcp.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private VBox nameContainer;

    @FXML
    private HBox orContainer;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label errorMessage;

    private Boolean isCreatingAccount = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameContainer.setManaged(false);
        nameContainer.setVisible(false);

        this.showError(false);
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Client.request(new JSONObject("{ \"operacao\": \"loginCandidato\" }"));
    }

    @FXML
    protected void onChangeToCreateAccount() {
        this.showError(false);

        if(isCreatingAccount) {
            nameContainer.setManaged(false);
            nameContainer.setVisible(false);
            orContainer.setManaged(true);
            orContainer.setVisible(true);

            createAccountButton.setText("Crie sua conta");
            submitButton.setText("Entrar");
        } else {
            nameContainer.setManaged(true);
            nameContainer.setVisible(true);
            orContainer.setManaged(false);
            orContainer.setVisible(false);

            submitButton.setText("Criar");
            createAccountButton.setText("Voltar");
        }

        isCreatingAccount = !isCreatingAccount;
    }

    @FXML
    protected void onSubmit() {
        this.showError(false);

        JSONObject data;

        if(isCreatingAccount) {
            data = new JSONObject("{ \"operacao\": \"cadastrarCandidato\" }");
            data.put("email", email.getText());
            data.put("senha", password.getText());
            data.put("nome", name.getText());
        } else {
            data = new JSONObject("{ \"operacao\": \"loginCandidato\" }");
            data.put("email", email.getText());
            data.put("senha", password.getText());
        }

        disableAllTextFields(true);

        try {
            JSONObject response = Client.request(data);

            if(response.getInt("status") == 401) {
                this.showError(true);

                errorMessage.setText(response.getString("mensagem"));
            } else {
                // login
            }

        } catch (IOException error) {
            // snackbar
        } finally {
            disableAllTextFields(false);
        }
    }

    private void disableAllTextFields(Boolean value) {
        name.setDisable(value);
        password.setDisable(value);
        email.setDisable(value);
    }

    private void showError(Boolean value) {
        String AddErrorStyle = "-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: red;";
        String removeErrorStyle = "-fx-border-width: none; -fx-border-style: none; -fx-border-color: none;";

        errorMessage.setManaged(value);
        errorMessage.setVisible(value);

        name.setStyle(value ? AddErrorStyle : removeErrorStyle);
        password.setStyle(value ? AddErrorStyle : removeErrorStyle);
        email.setStyle(value ? AddErrorStyle : removeErrorStyle);
    }
}