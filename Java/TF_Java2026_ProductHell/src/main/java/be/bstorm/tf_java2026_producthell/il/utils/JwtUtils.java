package be.bstorm.tf_java2026_producthell.il.utils;

import be.bstorm.tf_java2026_producthell.dl.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private final Long expiration;
    private final JwtBuilder builder;
    private final JwtParser parser;

    public JwtUtils(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration}") Long expiration
    ) {
        this.expiration = expiration;
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        this.builder = Jwts.builder().signWith(secretKey);
        this.parser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String generateToken(User user){
        return builder
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    private Claims parse(String token){
        return parser.parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token){
        return parse(token).getSubject();
    }

    public Integer getId(String token){
        return parse(token).get("id", Integer.class);
    }

    public List<String> getAuthorities(String token){
        return parse(token).get("roles", List.class);
    }

    public boolean isValidToken(String token){
        Claims claims = parse(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
