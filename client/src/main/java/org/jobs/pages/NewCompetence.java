package org.jobs.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jobs.tcp.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewCompetence implements Initializable {
    @FXML
    TextField experience;

    @FXML
    ChoiceBox<String> competence;

    String[] competences = {
            "Python",
            "C#",
            "C++",
            "JS",
            "PHP",
            "Swift",
            "Java",
            "Go",
            "SQL",
            "Ruby",
            "HTML",
            "CSS",
            "NOSQL",
            "Flutter",
            "TypeScript",
            "Perl",
            "Cobol",
            "dotNet",
            "Kotlin",
            "Dart"
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        competence.getItems().addAll(competences);
    }

    public void onSave(ActionEvent actionEvent) {
        var payload = new JSONObject("{ \"operacao\": \"cadastrarCompetenciaExperiencia\" }");

        payload.put("email", Client.getEmail());
        payload.put("token", Client.getToken());

        var competenceExperience = new JSONObject();

        competenceExperience.put("competencia", competence.getValue());
        competenceExperience.put("experiencia", Integer.parseInt(experience.getText()));

        var competencesArr = new JSONArray();
        competencesArr.put(competenceExperience);

        payload.put("competenciaExperiencia", competencesArr);

        try {
            JSONObject response = Client.getInstance().request(payload);

            if (response == null) {
                return;
            }

            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
