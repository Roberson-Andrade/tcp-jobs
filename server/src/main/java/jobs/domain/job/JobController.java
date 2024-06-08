package jobs.domain.job;

import java.util.List;

import org.json.JSONObject;

import codegen.jooq.tables.records.JobRecord;
import jobs.domain.job.dto.AddJobInDTO;
import jobs.domain.job.dto.AddJobOutDTO;
import jobs.domain.job.dto.DeleteJobInDTO;
import jobs.domain.job.dto.DeleteJobOutDTO;
import jobs.domain.job.dto.FindAllJobInDTO;
import jobs.domain.job.dto.FindAllJobOutDTO;
import jobs.domain.job.dto.GetJobByIdInDTO;
import jobs.domain.job.dto.GetJobByIdOutDTO;
import jobs.domain.job.dto.JobDTO;
import jobs.domain.job.dto.UpdateJobInDTO;
import jobs.domain.job.dto.UpdateJobOutDTO;
import jobs.errors.ApplicationException;
import jobs.utils.controller.BaseController;

public class JobController extends BaseController {
  final JobRepository jobRepository;

  public JobController(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  public JSONObject addJob(JSONObject input) throws ApplicationException {
    AddJobInDTO data = new AddJobInDTO(input);

    this.jobRepository.create(data);

    return this.json(new AddJobOutDTO(201, "Vaga criada com sucesso"));
  }

  public JSONObject findAll(JSONObject input) throws ApplicationException {
    FindAllJobInDTO data = new FindAllJobInDTO(input);

    List<JobRecord> jobs = this.jobRepository.findAllByEmail(data.getEmail());

    return this.json(new FindAllJobOutDTO(201, jobs));
  }

  public JSONObject getJobById(JSONObject input) throws ApplicationException {
    GetJobByIdInDTO data = new GetJobByIdInDTO(input);

    JobRecord job = this.jobRepository.find(data.getId());

    return this.json(new GetJobByIdOutDTO(201, job));
  }

  public JSONObject delete(JSONObject input) throws ApplicationException {
    DeleteJobInDTO data = new DeleteJobInDTO(input);

    this.jobRepository.delete(data.getEmail(), data.getId());

    return this.json(new DeleteJobOutDTO(201, "Vaga removida com sucesso"));
  }

  public JSONObject update(JSONObject input) throws ApplicationException {
    UpdateJobInDTO data = new UpdateJobInDTO(input);

    this.jobRepository.update(data.getId(), new JobDTO(data.getName(), data.getField(), data.getSalaryRange(),
        data.getExperience(), data.getDescription()));

    return this.json(new UpdateJobOutDTO(201, "Vaga atualizada com sucesso"));
  }
}
