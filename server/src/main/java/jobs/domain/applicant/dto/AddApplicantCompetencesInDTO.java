package jobs.domain.applicant.dto;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class AddApplicantCompetencesInDTO {

  String email;
  ArrayList<ApplicantCompetenceRecordIn> applicantCompetence;

  public AddApplicantCompetencesInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email", null);
    this.applicantCompetence = new ArrayList<ApplicantCompetenceRecordIn>();

    JSONArray array = input.optJSONArray("competenciaExperiencia", null);

    if (array == null || email == null) {
      throw new ApplicationException("Parametros inválidos", 422);
    }

    for (int i = 0; i < array.length(); i++) {
      this.applicantCompetence.add(new ApplicantCompetenceRecordIn(array.getJSONObject(i).optString("competencia"),
          array.getJSONObject(i).optInt("experiencia")));
    }
  }

  public String getEmail() {
    return email;
  }

  public ArrayList<ApplicantCompetenceRecordIn> getApplicantCompetence() {
    return applicantCompetence;
  }
}
