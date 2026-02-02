package br.com.rh.gestaovagas.modules.company.usecases;

import br.com.rh.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.rh.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.rh.gestaovagas.modules.company.entities.JobEntity;
import br.com.rh.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.rh.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateJobUseCase {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public CreateJobUseCase(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public JobEntity execute(CreateJobDTO dto, String companyId) {

        UUID companyUUID = UUID.fromString(companyId);

        CompanyEntity company = companyRepository.findById(companyUUID)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));

        JobEntity job = new JobEntity();
        job.setDescription(dto.description());
        job.setBenefits(dto.benefits());
        job.setLevel(dto.level());
        job.setCompany(company);

        return jobRepository.save(job);
    }
}
