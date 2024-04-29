package org.jobs.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jobs.app.App;
import org.jobs.tcp.Client;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    TextField email, name;
    @FXML
    PasswordField password;
    @FXML
    Button cancelButton;
    Boolean disabledButtons = true;

    public void setData(String email) {
        var payload = new JSONObject("{ \"operacao\": \"visualizarCandidato\" }");

        payload.put("email", email);

        try {
            JSONObject response = Client.request(payload);

            int token = response.getInt("status");

            if (token == 404) {
                System.err.println(response.getString("mensagem"));
            } else {
                this.email.setText(email);
                this.name.setText(response.getString("nome"));
                this.password.setText(response.getString("senha"));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void onToggleDisabled(ActionEvent actionEvent) {
        if (disabledButtons) {
            this.editFields(true);
        } else {
            this.editProfile();
            this.editFields(false);
        }
    }

    private void editProfile() {
        var payload = new JSONObject("{ \"operacao\": \"atualizarCandidato\" }");

        payload.put("email", email.getText());
        payload.put("nome", name.getText());
        payload.put("senha", password.getText());

        try {
            Client.request(payload);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void onDeleteProfile(ActionEvent event) {
        var payload = new JSONObject("{ \"operacao\": \"apagarCandidato\" }");

        payload.put("email", email.getText());

        try {
            Client.request(payload);
            this.goToLogin(event);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void editFields(Boolean value) {
        name.setDisable(!value);
        password.setDisable(!value);
        disabledButtons = !value;
        showCancelButton(value);
    }

    public void onCancel(ActionEvent actionEvent) {
        this.editFields(false);
    }

    private void showCancelButton(Boolean value) {
        cancelButton.setVisible(value);
        cancelButton.setManaged(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCancelButton(false);
    }

    @FXML
    public void onLogOut(ActionEvent actionEvent) {
        var payload = new JSONObject("{ \"operacao\": \"logout\" }");

        payload.put("token", Client.getToken());

        try {
            Client.request(payload);

            this.goToLogin(actionEvent);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goToLogin(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        currentStage.setScene(scene);
        currentStage.show();
    }
}
