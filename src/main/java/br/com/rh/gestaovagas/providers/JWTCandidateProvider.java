package br.com.rh.gestaovagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JWTCandidateProvider {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public DecodedJWT validateToken(String token) {

        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            return JWT.require(algorithm)
                    .withIssuer("javagas") // ✅ garante que é do seu sistema
                    .build()
                    .verify(token);

        } catch (JWTVerificationException ex) {

            log.warn("Token JWT do candidato inválido: {}", ex.getMessage());
            return null;
        }
    }
}
