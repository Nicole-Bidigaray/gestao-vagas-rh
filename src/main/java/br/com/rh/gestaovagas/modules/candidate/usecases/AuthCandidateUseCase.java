package br.com.rh.gestaovagas.modules.candidate.usecases;

import br.com.rh.gestaovagas.modules.candidate.CandidateRepository;
import br.com.rh.gestaovagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.rh.gestaovagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCandidateUseCase(CandidateRepository candidateRepository,
                                PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO dto) {

        var candidate = candidateRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        boolean passwordMatches = passwordEncoder.matches(dto.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Duration duration = Duration.ofHours(2);
        Instant expiresAt = Instant.now().plus(duration);

        String token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withExpiresAt(expiresAt) // ✅ Instant (não epoch)
                .sign(algorithm);

        return new AuthCandidateResponseDTO(token, duration.getSeconds()); // ✅ 7200
    }
}
