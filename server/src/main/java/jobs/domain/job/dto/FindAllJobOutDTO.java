package jobs.domain.job.dto;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import codegen.jooq.tables.records.JobRecord;
import jobs.utils.dto.BaseOutDTO;

public class FindAllJobOutDTO extends BaseOutDTO {
  private JSONArray vagas = new JSONArray();

  public FindAllJobOutDTO(Integer status, List<JobRecord> jobs) {
    super(status);

    for (JobRecord job : jobs) {
      var obj = new JSONObject();
      obj.put("idVaga", job.getId());
      obj.put("nome", job.getName());
      vagas.put(obj);

    }
  }

  public JSONArray getVagas() {
    return vagas;
  }
}
