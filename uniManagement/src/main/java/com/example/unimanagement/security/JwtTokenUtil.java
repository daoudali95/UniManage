package com.example.unimanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@NoArgsConstructor
@Component
public class JwtTokenUtil implements Serializable {

//    @Value("${jwt.secret}")
//    private String secret;

    private GetToken getToken;
//    @Autowired
//    private TokenWrapper tokenWrapper;

    private Claims getClaimsFromToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

//   public String getToken(){
//        String token = tokenWrapper.getToken();
//        return token;
//   }
//
//   public String token = getToken.geTToken();

//    public String token = getToken.geTToken();


    public String getUsernameFromToken(GetToken getToken) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(getToken.geTToken());
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
}