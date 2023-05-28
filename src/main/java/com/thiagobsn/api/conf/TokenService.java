package com.thiagobsn.api.conf;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thiagobsn.api.domain.usuario.entity.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API Spring-boot-3")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt.", exception);
        }
    }


    public String getSubject(String tokenJWT) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                // specify an specific claim validations
                .withIssuer("API Spring-boot-3")
                // reusable verifier instance
                .build()
                .verify(tokenJWT)
                .getSubject();    
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado.");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
