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
import org.jobs.models.Job;
import org.jobs.models.JobData;
import org.jobs.tcp.Client;
import org.jobs.utils.Router;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MyJobsController implements Initializable {
    @FXML
    private TableView<Job> tableView;
    @FXML
    private TableColumn<Job, Integer> id;
    @FXML
    private TableColumn<Job, String> name;
    @FXML
    private TableColumn<Job, Void> actions;
    private ObservableList<Job> tableData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Job, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Job, String>("name"));

        this.setupData();

        tableView.setItems(tableData); // Ensure setItems is called after tableData is populated

        addButtonToTable();
    }

    public void onClickExit(ActionEvent actionEvent) {
        Router.redirect("menu-company.fxml", actionEvent);
    }

    public void setupData() {
        var payload = new JSONObject("{ \"operacao\": \"listarVagas\" }");

        payload.put("email", Client.getEmail());
        payload.put("token", Client.getToken());

        try {
            JSONObject response = Client.getInstance().request(payload);

            if (response == null) {
                return;
            }

            JSONArray arr = response.getJSONArray("vagas");

            ArrayList<Job> results = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = arr.getJSONObject(i);
                results.add(new Job(item.getInt("idVaga"), item.getString("nome")));
            }

            this.tableData.setAll(results); // Update the observable list
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void onAdd() {
        // Create the custom dialog.
        Dialog<JobData> dialog = new Dialog<>();
        dialog.setTitle("Novo Trabalho");
        dialog.setHeaderText("Adicione um novo trabalho");

        // Set the button types.
        ButtonType saveButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the inputs.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField faixaSalarialField = new TextField();
        faixaSalarialField.setPromptText("Faixa Salarial");

        TextField descricaoField = new TextField();
        descricaoField.setPromptText("Descrição");

        TextField estadoField = new TextField();
        estadoField.setPromptText("Estado");

        ListView<String> competenciasListView = new ListView<>(FXCollections.observableArrayList(
                "Python", "C#", "C++", "JS", "PHP", "Swift", "Java", "Go",
                "SQL", "Ruby", "HTML", "CSS", "NOSQL", "Flutter", "TypeScript",
                "Perl", "Cobol", ".NET", "Kotlin", "Dart"));
        competenciasListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Faixa Salarial:"), 0, 1);
        grid.add(faixaSalarialField, 1, 1);
        grid.add(new Label("Descrição:"), 0, 2);
        grid.add(descricaoField, 1, 2);
        grid.add(new Label("Estado:"), 0, 3);
        grid.add(estadoField, 1, 3);
        grid.add(new Label("Competências:"), 0, 4);
        grid.add(competenciasListView, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the nome field by default.
        Platform.runLater(() -> nomeField.requestFocus());

        // Convert the result to a JobData when the save button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String nome = nomeField.getText();
                int faixaSalarial = Integer.parseInt(faixaSalarialField.getText());
                String descricao = descricaoField.getText();
                String estado = estadoField.getText();
                ObservableList<String> competencias = competenciasListView.getSelectionModel().getSelectedItems();
                return new JobData(nome, faixaSalarial, descricao, estado, competencias);
            }
            return null;
        });

        Optional<JobData> result = dialog.showAndWait();

        result.ifPresent(jobData -> {
            var payload = new JSONObject("{ \"operacao\": \"cadastrarVaga\" }");

            payload.put("email", Client.getEmail());
            payload.put("token", Client.getToken());
            payload.put("nome", jobData.getNome());
            payload.put("faixaSalarial", jobData.getFaixaSalarial());
            payload.put("estado", jobData.getEstado());
            payload.put("competencias", jobData.getCompetencias());
            payload.put("descricao", jobData.getDescricao());

            try {
                Client.getInstance().request(payload);
                this.setupData();
            } catch (IOException e) {
                System.err.println(e);
            }

        });
    }

    public void editJob(Integer id) {
        var existingJobData = getJobData(id);

        if (existingJobData == null) {
            return;
        }

        // Create the custom dialog.
        Dialog<JobData> dialog = new Dialog<>();
        dialog.setTitle("Editar Trabalho");
        dialog.setHeaderText("Edite o trabalho");

        // Set the button types.
        ButtonType saveButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the inputs.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField faixaSalarialField = new TextField();
        faixaSalarialField.setPromptText("Faixa Salarial");

        TextField descricaoField = new TextField();
        descricaoField.setPromptText("Descrição");

        TextField estadoField = new TextField();
        estadoField.setPromptText("Estado");

        ListView<String> competenciasListView = new ListView<>(FXCollections.observableArrayList(
                "Python", "C#", "C++", "JS", "PHP", "Swift", "Java", "Go",
                "SQL", "Ruby", "HTML", "CSS", "NOSQL", "Flutter", "TypeScript",
                "Perl", "Cobol", ".NET", "Kotlin", "Dart"));
        competenciasListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        if (existingJobData != null) {
            nomeField.setText(existingJobData.getNome());
            faixaSalarialField.setText(String.valueOf(existingJobData.getFaixaSalarial()));
            descricaoField.setText(existingJobData.getDescricao());
            estadoField.setText(existingJobData.getEstado());
            competenciasListView.getSelectionModel().selectIndices(-1, existingJobData.getCompetencias().stream()
                    .mapToInt(competenciasListView.getItems()::indexOf).toArray());
        }

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Faixa Salarial:"), 0, 1);
        grid.add(faixaSalarialField, 1, 1);
        grid.add(new Label("Descrição:"), 0, 2);
        grid.add(descricaoField, 1, 2);
        grid.add(new Label("Estado:"), 0, 3);
        grid.add(estadoField, 1, 3);
        grid.add(new Label("Competências:"), 0, 4);
        grid.add(competenciasListView, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the nome field by default.
        Platform.runLater(() -> nomeField.requestFocus());

        // Convert the result to a JobData when the save button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String nome = nomeField.getText();
                int faixaSalarial = Integer.parseInt(faixaSalarialField.getText());
                String descricao = descricaoField.getText();
                String estado = estadoField.getText();
                ObservableList<String> competencias = competenciasListView.getSelectionModel().getSelectedItems();
                return new JobData(nome, faixaSalarial, descricao, estado, competencias);
            }
            return null;
        });

        Optional<JobData> result = dialog.showAndWait();

        result.ifPresent(jobData -> {
            var payload = new JSONObject("{ \"operacao\": \"atualizarVaga\" }");

            payload.put("email", Client.getEmail());
            payload.put("token", Client.getToken());
            payload.put("idVaga", id);
            payload.put("nome", jobData.getNome());
            payload.put("faixaSalarial", jobData.getFaixaSalarial());
            payload.put("estado", jobData.getEstado());
            payload.put("competencias", jobData.getCompetencias());
            payload.put("descricao", jobData.getDescricao());

            try {
                Client.getInstance().request(payload);
                this.setupData();
            } catch (IOException e) {
                System.err.println(e);
            }
        });
    }

    private JobData getJobData(Integer id) {
        var payload = new JSONObject("{ \"operacao\": \"visualizarVaga\" }");

        payload.put("email", Client.getEmail());
        payload.put("token", Client.getToken());
        payload.put("idVaga", id);

        try {
            var response = Client.getInstance().request(payload);

            if (response == null) {
                return null;
            }

            var name = response.optString("nome", null);
            var faixaSalarial = response.optInt("faixaSalarial", -1);
            var descricao = response.optString("descricao", null);
            var estado = response.optString("estado", null);
            var competencias = response.optJSONArray("competencias", null);

            if (name == null || faixaSalarial == -1 || descricao == null || estado == null || competencias == null) {
                Client.showError("Vaga não encontrada");
                return null;
            }

            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i < competencias.length(); i++) {
                list.add(competencias.getString(i));
            }

            return new JobData(name, faixaSalarial, descricao, estado, FXCollections.observableArrayList(list));
        } catch (IOException e) {
            System.err.println(e);
        }

        return null;
    }

    private void addButtonToTable() {
        Callback<TableColumn<Job, Void>, TableCell<Job, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Job, Void> call(final TableColumn<Job, Void> param) {
                final TableCell<Job, Void> cell = new TableCell<>() {
                    private final Button editBtn = new Button("Editar");
                    private final Button deleteBtn = new Button("Excluir");

                    {
                        editBtn.setOnAction((ActionEvent event) -> {
                            Job data = getTableView().getItems().get(getIndex());
                            editJob(data.getId());
                        });

                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Job data = getTableView().getItems().get(getIndex());
                            deleteJob(data);
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

    private void deleteJob(Job data) {
        var payload = new JSONObject("{ \"operacao\": \"apagarVaga\" }");

        payload.put("email", Client.getEmail());
        payload.put("token", Client.getToken());
        payload.put("idVaga", data.getId());

        try {
            Client.getInstance().request(payload);

            setupData();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
