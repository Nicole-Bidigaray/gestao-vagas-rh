package br.com.rh.gestaovagas.modules.candidate.usecases;

import br.com.rh.gestaovagas.exceptions.UserFoundException;
import br.com.rh.gestaovagas.modules.candidate.CandidateEntity;
import br.com.rh.gestaovagas.modules.candidate.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateEntity execute(CandidateEntity candidate) {

        if (candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).isPresent()) {
            throw new UserFoundException();
        }

        return candidateRepository.save(candidate);
    }
}
