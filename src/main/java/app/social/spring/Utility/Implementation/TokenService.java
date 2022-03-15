package app.social.spring.Utility.Implementation;

import app.social.spring.Utility.Config.UserPrincipal;
import app.social.spring.Utility.JWT.JwtProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private String generateToken(Authentication authResult) {
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
        System.out.println(principal.getUsername());

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
        return token;
    }
}
