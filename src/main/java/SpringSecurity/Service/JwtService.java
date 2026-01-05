package SpringSecurity.Service;


import SpringSecurity.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private  String jwtsecretkey;

    public String generateJwtToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("Username",user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+60*60*1000))
                .signWith(getJwtsecretkey(),SignatureAlgorithm.HS256)
                .compact();
    }


    public SecretKey getJwtsecretkey(){
        return Keys.hmacShaKeyFor(jwtsecretkey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValid(String token,CustomUserDetails user){
        String username=getUserName(token);
        return username.equals(user.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return  extractClaimsJWT(token).getExpiration().before(new Date());
    }

    public String getUserName(String token){
        return extractClaimsJWT(token).getSubject();
    }

    private Claims extractClaimsJWT(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(getJwtsecretkey()).build().parseClaimsJws(jwtToken).getBody();
    }

}
