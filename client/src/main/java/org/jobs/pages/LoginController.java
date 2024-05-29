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

public class LoginController implements Initializable {
    @FXML
    private VBox nameContainer;
    @FXML
    private HBox orContainer;
    @FXML
    private TextField name, email, password;
    @FXML
    private Button createAccountButton, submitButton;
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
    protected void onChangeToCreateAccount() {
        this.showError(false);

        if (isCreatingAccount) {
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

        if (isCreatingAccount) {
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
            JSONObject response = Client.getInstance().request(data);

            if (response == null) {
                return;
            }

            Client.setToken(response.getString("token"));
            if(isCreatingAccount) {
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
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("profile.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ProfileController controller = fxmlLoader.getController();

                controller.setData(email.getText());

                currentStage.setScene(scene);
                currentStage.show();
            }
        } catch (Exception error) {
            Client.showError(error.getMessage());
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