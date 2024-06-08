package org.jobs.pages;

import javafx.event.ActionEvent;
import org.jobs.tcp.Client;
import org.jobs.utils.Router;
import org.json.JSONObject;

import java.io.IOException;

public class MenuCompanyController {
    public void onClickCompany(ActionEvent actionEvent) {
    }

    public void onClickJobs(ActionEvent actionEvent) {
    }

    public void onClickLogout(ActionEvent actionEvent) {
        var payload = new JSONObject("{ \"operacao\": \"logout\" }");

        payload.put("token", Client.getToken());

        try {
            Client.getInstance().request(payload);

            Router.redirect("login-profile.fxml", actionEvent);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
