package org.jobs.pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.jobs.models.FilterJobData;
import org.jobs.models.FilteredJob;
import org.jobs.tcp.Client;
import org.jobs.utils.Router;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JobsController implements Initializable {
    @FXML
    private TableView<FilteredJob> tableView;
    @FXML
    private TableColumn<FilteredJob, Integer> id;
    @FXML
    private TableColumn<FilteredJob, String> name, salary, description, state, competences;
    @FXML
    private TableColumn<FilteredJob, Void> actions;
    private ObservableList<FilteredJob> tableData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<FilteredJob, Integer>("idVaga"));
        name.setCellValueFactory(new PropertyValueFactory<FilteredJob, String>("nome"));
        salary.setCellValueFactory(new PropertyValueFactory<FilteredJob, String>("faixaSalarial"));
        description.setCellValueFactory(new PropertyValueFactory<FilteredJob, String>("descricao"));
        state.setCellValueFactory(new PropertyValueFactory<FilteredJob, String>("estado"));
        competences.setCellValueFactory(new PropertyValueFactory<FilteredJob, String>("competencias"));

        tableView.setItems(tableData);
    }

    public void onClickExit(ActionEvent actionEvent) {
        Router.redirect("menu-company.fxml", actionEvent);
    }

    public void onFilter() {
        // Create the custom dialog.
        Dialog<FilterJobData> dialog = new Dialog<>();
        dialog.setTitle("Filtrar Trabalhos");
        dialog.setHeaderText("Filtre os trabalhos por competências");

        // Set the button types.
        ButtonType filterButtonType = new ButtonType("Filtrar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(filterButtonType, ButtonType.CANCEL);

        // Create the inputs.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ListView<String> competenciasListView = new ListView<>(FXCollections.observableArrayList(
                "Python", "C#", "C++", "JS", "PHP", "Swift", "Java", "Go",
                "SQL", "Ruby", "HTML", "CSS", "NOSQL", "Flutter", "TypeScript",
                "Perl", "Cobol", "dotNet", "Kotlin", "Dart"));
        competenciasListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("OR", "AND"));
        choiceBox.setValue("OR");

        grid.add(new Label("Competências:"), 0, 0);
        grid.add(competenciasListView, 1, 0);
        grid.add(new Label("Tipo de Filtro:"), 0, 1);
        grid.add(choiceBox, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a FilterJobData when the filter button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == filterButtonType) {
                ObservableList<String> competencias = competenciasListView.getSelectionModel().getSelectedItems();
                String tipo = choiceBox.getValue();
                return new FilterJobData(competencias, tipo);
            }
            return null;
        });

        Optional<FilterJobData> result = dialog.showAndWait();

        result.ifPresent(filterJobData -> {
            var payload = new JSONObject("{ \"operacao\": \"filtrarVagas\" }");
            var filter = new JSONObject();
            payload.put("email", Client.getEmail());
            payload.put("token", Client.getToken());

            filter.put("competencias", filterJobData.getCompetencias());
            filter.put("tipo", filterJobData.getTipo());

            payload.put("filtros", filter);
            try {
                JSONObject response = Client.getInstance().request(payload);

                if (response == null) {
                    return;
                }

                // continue here

                List<FilteredJob> results = new ArrayList<>();
                JSONArray jobsArray = response.optJSONArray("vagas", null);
                if(jobsArray == null) {
                    return;
                }
                for (int i = 0; i < jobsArray.length(); i++) {
                    JSONObject jobObject = jobsArray.getJSONObject(i);

                    // Check if all fields exist in the job object
                    if (!jobObject.has("idVaga") || !jobObject.has("nome") || !jobObject.has("faixaSalarial")
                            || !jobObject.has("descricao") || !jobObject.has("estado")
                            || !jobObject.has("competencias")) {
                        Client.showError("Vaga não encontrada");
                        return;
                    }

                    // Extract fields from the job object
                    int idVaga = jobObject.getInt("idVaga");
                    String nome = jobObject.getString("nome");
                    int faixaSalarial = jobObject.getInt("faixaSalarial");
                    String descricao = jobObject.getString("descricao");
                    String estado = jobObject.getString("estado");
                    JSONArray competenciasArray = jobObject.getJSONArray("competencias");
                    StringBuilder competenciasBuilder = new StringBuilder();
                    for (int j = 0; j < competenciasArray.length(); j++) {
                        competenciasBuilder.append(competenciasArray.getString(j));
                        if (j < competenciasArray.length() - 1) {
                            competenciasBuilder.append(", ");
                        }
                    }
                    String competencias = competenciasBuilder.toString();
                    results.add(new FilteredJob(idVaga, nome, faixaSalarial, descricao, estado, competencias));
                }

                this.tableData.setAll(results);
            } catch (IOException e) {
                System.err.println(e);
            }
        });
    }
}
