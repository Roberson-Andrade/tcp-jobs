package jobs.domain.applicant.dto;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import jobs.errors.ApplicationException;

public class UpdateApplicantCompetenceInDTO {

  private final String email;

  ArrayList<ApplicantCompetenceRecordIn> applicantCompetence;

  public UpdateApplicantCompetenceInDTO(JSONObject input) throws ApplicationException {
    this.email = input.optString("email");
    this.applicantCompetence = new ArrayList<ApplicantCompetenceRecordIn>();

    JSONArray array = input.optJSONArray("competencias");

    if (array == null || email == null) {
      throw new ApplicationException("Parametros inválidos", 422);
    }

    for (int i = 0; i < array.length(); i++) {
      this.applicantCompetence.add(new ApplicantCompetenceRecordIn(array.getJSONObject(i).optString("email"),
          array.getJSONObject(i).optInt("competenciaExperiencia")));
    }
  }

  public String getEmail() {
    return email;
  }

  public ArrayList<ApplicantCompetenceRecordIn> getApplicantCompetence() {
    return applicantCompetence;
  }
}
