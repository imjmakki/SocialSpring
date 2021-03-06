package app.social.spring.API;

import app.social.spring.DTO.JwtLogin;
import app.social.spring.DTO.LoginResponse;
import app.social.spring.DTO.TokenDTO;
import app.social.spring.Entity.Role;
import app.social.spring.Entity.User;
import app.social.spring.Service.RoleService;
import app.social.spring.Service.UserService;
import app.social.spring.Utility.Implementation.TokenService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/social")
@CrossOrigin(origins = "*")
public class SocialResource {

    private UserService userService;
    private RoleService roleService;
    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;

    private String email;

    @Value("${google.id}")
    private String IdClient;

    @Value("${secret.password}")
    private String password;

    @Autowired
    public SocialResource(UserService userService, RoleService roleService, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws Exception {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder ver =
                new GoogleIdTokenVerifier.Builder(transport, factory)
                        .setAudience(Collections.singleton(IdClient));
        GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(), tokenDTO.getToken());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        email = payload.getEmail();
        User user = new User();
        if(userService.ifEmailExist(email)) {
            user = userService.getUserByMail(email);
        } else {
            user = createUser(email);
        }

        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(user.getPassword());
        //use this if you already have a password
        //jwtLogin.setPassword(password);

        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), OK);
    }

    private User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = roleService.getRoles();
        user.getRoles().add(roles.get(0));
        return userService.saveUser(user);
    }

    @PostMapping("/facebook")
    public ResponseEntity<LoginResponse> loginWithFacebook(@RequestBody TokenDTO tokenDTO) throws Exception {
        Facebook facebook = new FacebookTemplate(tokenDTO.getToken());
        String[] data = { "email", "name", "picture" };
        //importing facebook user instead of entity
        org.springframework.social.facebook.api.User user = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, data);
        email = user.getEmail();
        User faceUser = new User();
        if(userService.ifEmailExist(email)) {
            faceUser = userService.getUserByMail(email);
        } else {
            faceUser = createUser(email);
        }

        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);

        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), OK);
    }
}
