package org.jobs.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jobs.app.App;
import org.jobs.tcp.Client;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginCompanyController implements Initializable {
    @FXML
    private VBox businessNameContainer, sectorContainer, cnpjContainer, descriptionContainer;
    @FXML
    private HBox orContainer;
    @FXML
    private TextField businessName, email, password, cnpj, sector, description;
    @FXML
    private Button createAccountButton, submitButton;
    @FXML
    private Label errorMessage;
    private Boolean isCreatingAccount = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showCreateContainers(false);

        this.showError(false);
    }

    @FXML
    protected void onChangeToCreateAccount() {
        this.showError(false);

        if (isCreatingAccount) {
            this.showCreateContainers(false);
            orContainer.setManaged(true);
            orContainer.setVisible(true);

            createAccountButton.setText("Crie sua conta");
            submitButton.setText("Entrar");
        } else {
            this.showCreateContainers(true);
            orContainer.setManaged(false);
            orContainer.setVisible(false);

            submitButton.setText("Criar");
            createAccountButton.setText("Voltar");
        }

        isCreatingAccount = !isCreatingAccount;
    }

    private void showCreateContainers(Boolean value) {
        businessNameContainer.setManaged(value);
        businessNameContainer.setVisible(value);

        cnpjContainer.setManaged(value);
        cnpjContainer.setVisible(value);

        sectorContainer.setManaged(value);
        sectorContainer.setVisible(value);

        descriptionContainer.setManaged(value);
        descriptionContainer.setVisible(value);
    }

    @FXML
    protected void onOpenConfig() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("config.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Novo cliente");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ignored) {

        }
    }

    @FXML
    protected void onSubmit(ActionEvent event) {
        this.showError(false);

        JSONObject data;

        Client.setEmail(email.getText());

        if (isCreatingAccount) {
            data = new JSONObject("{ \"operacao\": \"cadastrarEmpresa\" }");
            data.put("email", email.getText());
            data.put("senha", password.getText());
            data.put("razaoSocial", businessName.getText());
            data.put("cnpj", cnpj.getText());
            data.put("ramo", sector.getText());
            data.put("descricao", description.getText());
        } else {
            data = new JSONObject("{ \"operacao\": \"loginEmpresa\" }");
            data.put("email", email.getText());
            data.put("senha", password.getText());
        }

        disableAllTextFields(true);

        try {
            JSONObject response = Client.getInstance().request(data);

            if (response == null) {
                return;
            }

            Client.setToken(response.getString("token"));
            if (isCreatingAccount) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("error.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();

                    var errorController = (ErrorController) fxmlLoader.getController();
                    errorController.setErrorMessage("Conta criada com sucesso");

                    stage.setTitle("Conta criada");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException ignored) {
                }
            } else {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu-company.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                currentStage.setScene(scene);
                currentStage.show();
            }
        } catch (Exception error) {
            System.err.println(error);
            Client.showError(error.getMessage());
        } finally {
            disableAllTextFields(false);
        }
    }

    private void disableAllTextFields(Boolean value) {
        businessName.setDisable(value);
        password.setDisable(value);
        email.setDisable(value);
    }

    private void showError(Boolean value) {
        String AddErrorStyle = "-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: red;";
        String removeErrorStyle = "-fx-border-width: none; -fx-border-style: none; -fx-border-color: none;";

        errorMessage.setManaged(value);
        errorMessage.setVisible(value);

        businessName.setStyle(value ? AddErrorStyle : removeErrorStyle);
        password.setStyle(value ? AddErrorStyle : removeErrorStyle);
        email.setStyle(value ? AddErrorStyle : removeErrorStyle);
    }
}