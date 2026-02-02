package br.com.rh.gestaovagas.modules.candidate.usecases;

import br.com.rh.gestaovagas.exceptions.UserFoundException;
import br.com.rh.gestaovagas.modules.candidate.CandidateEntity;
import br.com.rh.gestaovagas.modules.candidate.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCandidateUseCase(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateEntity execute(CandidateEntity candidate) {
        if (candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .isPresent()) {
            throw new UserFoundException();
        }

        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return candidateRepository.save(candidate);
    }
}
