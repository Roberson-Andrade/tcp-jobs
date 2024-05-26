package org.jobs.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jobs.app.App;

import java.io.IOException;

public class FirstController {
    public void onEnterAsCompany(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-company.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        currentStage.setScene(scene);
        currentStage.show();
    }

    public void onEnterAsApplicant(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        currentStage.setScene(scene);
        currentStage.show();
    }
}
