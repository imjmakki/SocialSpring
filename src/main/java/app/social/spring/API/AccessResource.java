package app.social.spring.API;

import app.social.spring.DTO.JwtLogin;
import app.social.spring.DTO.LoginResponse;
import app.social.spring.Utility.Implementation.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access")
@CrossOrigin(origins = "*")
public class AccessResource {

    private TokenService tokenService;

    @Autowired
    public AccessResource(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody JwtLogin jwtLogin) throws Exception {
        return tokenService.login(jwtLogin);
    }
}
