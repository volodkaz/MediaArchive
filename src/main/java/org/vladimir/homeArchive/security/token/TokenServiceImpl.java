package org.vladimir.homeArchive.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Value("${auth.jwt.key}")
    private String secretKey;

    @Override
    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try{
            DecodedJWT jwt = verifier.verify(token);
            if(!jwt.getIssuer().equals("auth-service")){
                log.error("Issuer is incorrect");
                return false;
            }
            if(!jwt.getAudience().contains("homearchive")){
                log.error("Audience is incorrect");
                return false;
            }
        }catch (JWTVerificationException e){
            log.error("Token is invalid: " + e.getMessage());
            return false;
        }
        return true;
    }
}
