package org.jobs.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController {
    @FXML
    private Label errorMessage;

    public void setErrorMessage(String value) {
        errorMessage.setText(value);
    }

    public void onClose(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
}
