package br.com.rh.gestaovagas.modules.company.usecases;

import br.com.rh.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.rh.gestaovagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.rh.gestaovagas.modules.company.repositories.CompanyRepository;
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
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCompanyUseCase(
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) {

        var company = companyRepository.findByUsername(authCompanyDTO.username())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username/password incorrect")
                );

        if (!passwordEncoder.matches(authCompanyDTO.password(), company.getPassword())) {
            throw new BadCredentialsException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return new AuthCompanyResponseDTO(
                token,
                Duration.ofHours(2).getSeconds() // ou expiresIn.toEpochMilli()
        );
    }
}
