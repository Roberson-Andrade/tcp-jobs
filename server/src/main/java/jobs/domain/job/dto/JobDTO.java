package jobs.domain.job.dto;

import java.util.ArrayList;

public record JobDTO(String name,
                     Double salaryRange,
                     ArrayList<String> competences,
                     String description, String state) {
}
