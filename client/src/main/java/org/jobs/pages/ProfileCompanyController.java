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
import org.jobs.utils.Router;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileCompanyController implements Initializable {
    @FXML
    TextField email, businessName, cnpj, sector, description;
    @FXML
    PasswordField password;
    @FXML
    Button cancelButton;
    Boolean disabledButtons = true;

    public void setData(String email) {
        var payload = new JSONObject("{ \"operacao\": \"visualizarEmpresa\" }");

        payload.put("email", email);
        payload.put("token", Client.getToken());

        try {
            JSONObject response = Client.getInstance().request(payload);

            if (response == null) {
                return;
            }

            this.email.setText(email);
            this.businessName.setText(response.getString("razaoSocial"));
            this.cnpj.setText(response.getString("cnpj"));
            this.sector.setText(response.getString("ramo"));
            this.description.setText(response.getString("descricao"));
            this.password.setText(response.getString("senha"));
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
        var payload = new JSONObject("{ \"operacao\": \"atualizarEmpresa\" }");

        payload.put("email", email.getText());
        payload.put("token", Client.getToken());

        payload.put("razaoSocial", businessName.getText());
        payload.put("cnpj", cnpj.getText());
        payload.put("ramo", sector.getText());
        payload.put("descricao", description.getText());
        payload.put("senha", password.getText());

        try {
            Client.getInstance().request(payload);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void onDeleteProfile(ActionEvent event) {
        var payload = new JSONObject("{ \"operacao\": \"apagarEmpresa\" }");

        payload.put("email", email.getText());
        payload.put("token", Client.getToken());

        try {
            Client.getInstance().request(payload);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void editFields(Boolean value) {
        businessName.setDisable(!value);
        cnpj.setDisable(!value);
        sector.setDisable(!value);
        description.setDisable(!value);
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
        Router.redirect("menu-company.fxml", actionEvent);
    }
}
