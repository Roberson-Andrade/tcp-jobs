package vacancy.auth;

import com.company.project.jooq.codegen.tables.records.ApplicantRecord;
import org.json.JSONObject;
import vacancy.auth.dto.ApplicantDTO;
import vacancy.auth.dto.LoginInDTO;
import vacancy.auth.dto.LoginOutDTO;
import vacancy.auth.dto.SignInInDTO;
import vacancy.utils.dto.BaseController;

public class AuthController extends BaseController {
    final ApplicantRepository applicantRepository;

    public AuthController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public JSONObject login(JSONObject input) {
        LoginInDTO data = new LoginInDTO(input);

        ApplicantRecord applicantRecord = this.applicantRepository.findByEmail(data.getEmail());

        System.out.println(applicantRecord);

        return this.json(new LoginOutDTO("token"));
    }

    public JSONObject signIn(JSONObject input) {
        SignInInDTO data = new SignInInDTO(input);

        ApplicantRecord applicantRecord = this.applicantRepository.create(new ApplicantDTO(data.getEmail(), data.getPassword(), data.getName()));

        System.out.println(applicantRecord);

        return this.json(new LoginOutDTO("token"));
    }
}
