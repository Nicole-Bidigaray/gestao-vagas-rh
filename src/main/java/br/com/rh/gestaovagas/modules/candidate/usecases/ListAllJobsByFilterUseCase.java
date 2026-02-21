package br.com.rh.gestaovagas.modules.candidate.usecases;

import br.com.rh.gestaovagas.modules.company.entities.JobEntity;
import br.com.rh.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    private final JobRepository jobRepository;

    public ListAllJobsByFilterUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobEntity> execute(String filter) {
        return jobRepository.findByDescriptionWithCompany(filter);
    }
}
