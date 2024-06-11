package jobs.domain.job;

import codegen.jooq.tables.records.JobCompetenceRecord;
import codegen.jooq.tables.records.JobRecord;
import jobs.domain.job.dto.*;
import jobs.errors.ApplicationException;
import jobs.utils.controller.BaseController;
import org.json.JSONObject;

import java.util.List;

public class JobController extends BaseController {
    final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JSONObject addJob(JSONObject input) throws ApplicationException {
        AddJobInDTO data = new AddJobInDTO(input);

        this.jobRepository.create(data, data.getEmail());

        return this.json(new AddJobOutDTO(201, "Vaga criada com sucesso"));
    }

    public JSONObject findAll(JSONObject input) throws ApplicationException {
        FindAllJobInDTO data = new FindAllJobInDTO(input);

        List<JobRecord> jobs = this.jobRepository.findAllByEmail(data.getEmail());
        System.out.println("jobs: " + jobs);
        return this.json(new FindAllJobOutDTO(201, jobs));
    }

    public JSONObject getJobById(JSONObject input) throws ApplicationException {
        GetJobByIdInDTO data = new GetJobByIdInDTO(input);

        JobRecord job = this.jobRepository.find(data.getId());

        List<JobCompetenceRecord> competences = this.jobRepository.findCompetences(data.getId());

        return this.json(new GetJobByIdOutDTO(201, job, competences));
    }

    public JSONObject delete(JSONObject input) throws ApplicationException {
        DeleteJobInDTO data = new DeleteJobInDTO(input);

        this.jobRepository.delete(data.getEmail(), data.getId());

        return this.json(new DeleteJobOutDTO(201, "Vaga removida com sucesso"));
    }

    public JSONObject update(JSONObject input) throws ApplicationException {
        UpdateJobInDTO data = new UpdateJobInDTO(input);

        this.jobRepository.update(data.getId(), new JobDTO(data.getName(), data.getSalaryRange(),
                data.getCompetences(), data.getDescription(), data.getState()));

        return this.json(new UpdateJobOutDTO(201, "Vaga atualizada com sucesso"));
    }
}
