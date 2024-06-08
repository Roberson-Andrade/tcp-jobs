package org.jobs.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jobs.app.App;
import org.jobs.pages.ProfileController;

import java.io.IOException;

public class Router {
    public static void redirect(String path, ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}
