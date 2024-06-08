package org.jobs.pages;

import javafx.event.ActionEvent;
import org.jobs.tcp.Client;
import org.jobs.utils.Router;
import org.json.JSONObject;

import java.io.IOException;

public class MenuApplicantController {
    public void onClickCompetence(ActionEvent actionEvent) {
        Router.redirect("my-competences.fxml", actionEvent);
    }

    public void onClickProfile(ActionEvent actionEvent) {
        Router.redirect("profile.fxml", actionEvent);
    }

    public void onClickJobs(ActionEvent actionEvent)  {
        Router.redirect("my-jobs.fxml", actionEvent);
    }

    public void onClickLogout(ActionEvent actionEvent) {
        var payload = new JSONObject("{ \"operacao\": \"logout\" }");

        payload.put("token", Client.getToken());

        try {
            Client.getInstance().request(payload);

            Router.redirect("login.fxml", actionEvent);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
