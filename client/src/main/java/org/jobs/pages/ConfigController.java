package org.jobs.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jobs.tcp.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    @FXML
    private TextField ip, port;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ip.setText(Client.getIp());
        port.setText(Client.getPort().toString());
    }

    public void onSaveConfig(ActionEvent actionEvent) {
        Client.setIp(ip.getText());
        Client.setPort(Integer.parseInt(port.getText()));

        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
}
