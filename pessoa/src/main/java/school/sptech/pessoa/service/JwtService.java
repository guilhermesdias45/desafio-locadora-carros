package school.sptech.pessoa.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secretKeyPlain){
        try {
            byte[] secretBytes = java.util.Base64.getDecoder().decode(secretKeyPlain.trim());
            this.secretKey = Keys.hmacShaKeyFor(secretBytes);
        } catch (Exception e) {
            logger.error("Erro ao configurar a chave secreta: {}", e.getMessage());
            throw new IllegalArgumentException("Chave secreta inválida. Verifique o jwt.secret", e);
        }
    }

    public String gerarToken(String username){
        long tempoExpiracao = 1000 * 60 * 60;

        return Jwts.builder().subject(username).
                issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + tempoExpiracao))
                .signWith(secretKey).compact();
    }
}
