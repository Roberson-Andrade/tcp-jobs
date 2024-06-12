package org.jobs.pages;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Pair;
import org.jobs.models.CompetenceExperience;
import org.jobs.tcp.Client;
import org.jobs.utils.Router;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MyCompetencesController implements Initializable {
    @FXML
    private TableView<CompetenceExperience> tableView;
    @FXML
    private TableColumn<CompetenceExperience, String> competence;
    @FXML
    private TableColumn<CompetenceExperience, String> experience;
    @FXML
    private TableColumn<CompetenceExperience, Void> actions;
    private ObservableList<CompetenceExperience> tableData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        competence.setCellValueFactory(new PropertyValueFactory<CompetenceExperience, String>("competence"));
        experience.setCellValueFactory(new PropertyValueFactory<CompetenceExperience, String>("experience"));

        this.setupData();

        tableView.setItems(tableData); // Ensure setItems is called after tableData is populated

        addButtonToTable();
    }

    public void onClickExit(ActionEvent actionEvent) {
        Router.redirect("menu-applicant.fxml", actionEvent);
    }

    public void setupData() {
        var payload = new JSONObject("{ \"operacao\": \"visualizarCompetenciaExperiencia\" }");

        payload.put("email", Client.getEmail());
        payload.put("token", Client.getToken());

        try {
            JSONObject response = Client.getInstance().request(payload);

            if (response == null) {
                return;
            }

            JSONArray arr = response.getJSONArray("competenciaExperiencia");

            ArrayList<CompetenceExperience> results = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = arr.getJSONObject(i);
                results.add(new CompetenceExperience(item.getString("competencia"), item.getInt("experiencia")));
            }

            this.tableData.setAll(results); // Update the observable list
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void onAdd() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Nova Competência");
        dialog.setHeaderText("Adicione uma nova competência e experiência");

        // Set the button types.
        ButtonType saveButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the competence ChoiceBox and experience TextField.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ChoiceBox<String> competenceChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(
                "Python", "C#", "C++", "JS", "PHP", "Swift", "Java", "Go",
                "SQL", "Ruby", "HTML", "CSS", "NOSQL", "Flutter", "TypeScript",
                "Perl", "Cobol", ".NET", "Kotlin", "Dart"));
        TextField experienceField = new TextField();
        experienceField.setPromptText("Experiência");

        grid.add(new Label("Competência:"), 0, 0);
        grid.add(competenceChoiceBox, 1, 0);
        grid.add(new Label("Experiência:"), 0, 1);
        grid.add(experienceField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the competenceChoiceBox by default.
        Platform.runLater(() -> competenceChoiceBox.requestFocus());

        // Convert the result to a pair of competence and experience when the save
        // button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Pair<>(competenceChoiceBox.getValue(), experienceField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(competenceExperienceData -> {
            String competence = competenceExperienceData.getKey();
            String experience = competenceExperienceData.getValue();

            var payload = new JSONObject("{ \"operacao\": \"cadastrarCompetenciaExperiencia\" }");

            payload.put("email", Client.getEmail());
            payload.put("token", Client.getToken());

            var competenceExperience = new JSONObject();

            competenceExperience.put("competencia", competence);
            competenceExperience.put("experiencia", experience);

            var competencesArr = new JSONArray();
            competencesArr.put(competenceExperience);

            payload.put("competenciaExperiencia", competencesArr);

            try {
                JSONObject response = Client.getInstance().request(payload);

                if (response == null) {
                    return;
                }

                setupData();
            } catch (IOException e) {
                System.err.println(e);
            }
        });
    }

    private void addButtonToTable() {
        Callback<TableColumn<CompetenceExperience, Void>, TableCell<CompetenceExperience, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<CompetenceExperience, Void> call(final TableColumn<CompetenceExperience, Void> param) {
                final TableCell<CompetenceExperience, Void> cell = new TableCell<>() {
                    private final Button editBtn = new Button("Editar");
                    private final Button deleteBtn = new Button("Excluir");

                    {
                        editBtn.setOnAction((ActionEvent event) -> {
                            CompetenceExperience data = getTableView().getItems().get(getIndex());
                            showEditDialog(data);
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            CompetenceExperience data = getTableView().getItems().get(getIndex());
                            deleteCompetenceExperience(data);
                        });
                    }

                    private final HBox pane = new HBox(editBtn, deleteBtn);

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };

        actions.setCellFactory(cellFactory);
    }

    private void deleteCompetenceExperience(CompetenceExperience data) {
        var payload = new JSONObject("{ \"operacao\": \"apagarCompetenciaExperiencia\" }");

        payload.put("email", Client.getEmail());
        payload.put("token", Client.getToken());
        JSONArray arr = new JSONArray();

        JSONObject obj = new JSONObject();
        obj.put("competencia", data.getCompetence());
        obj.put("experiencia", data.getExperience());

        arr.put(obj);

        payload.put("competenciaExperiencia", arr);

        try {
            Client.getInstance().request(payload);

            setupData();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void showEditDialog(CompetenceExperience competenceExperience) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Editar Experiência");
        dialog.setHeaderText("Edite a experiência para " + competenceExperience.getCompetence());

        // Set the button types.
        ButtonType editButtonType = new ButtonType("Editar", ButtonType.OK.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);

        // Create the experience label and field.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField experienceField = new TextField();
        experienceField.setPromptText("Experiência");
        experienceField.setText(String.valueOf(competenceExperience.getExperience()));

        grid.add(new Label("Experiência:"), 0, 0);
        grid.add(experienceField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the experience field by default.
        Platform.runLater(() -> experienceField.requestFocus());

        // Convert the result to a competenceExperience when the edit button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                return new Pair<>(competenceExperience.getCompetence(), experienceField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(competenceExperienceData -> {
            String competence = competenceExperienceData.getKey();
            String experience = competenceExperienceData.getValue();

            var payload = new JSONObject("{ \"operacao\": \"atualizarCompetenciaExperiencia\" }");

            payload.put("email", Client.getEmail());
            payload.put("token", Client.getToken());
            JSONArray arr = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("competencia", competence);
            obj.put("experiencia", experience);

            arr.put(obj);

            payload.put("competenciaExperiencia", arr);

            try {
                Client.getInstance().request(payload);
                setupData();
            } catch (IOException e) {
                System.err.println(e);
            }
        });
    }
}
