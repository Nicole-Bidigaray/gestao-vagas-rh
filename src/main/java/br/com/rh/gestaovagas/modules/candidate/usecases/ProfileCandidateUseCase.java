package br.com.rh.gestaovagas.modules.candidate.usecases;

import br.com.rh.gestaovagas.modules.candidate.CandidateRepository;
import br.com.rh.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public ProfileCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {

        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new ProfileCandidateResponseDTO(
                candidate.getDescription(),
                candidate.getUsername(),
                candidate.getEmail(),
                candidate.getId(),
                candidate.getName()
        );
    }
}
