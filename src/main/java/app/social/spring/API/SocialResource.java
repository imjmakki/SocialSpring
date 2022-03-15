package app.social.spring.API;

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
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Value("${google.id}")
    private String IdClient;

    @Value("${secret.password}")
    private String password;

    @Autowired
    public SocialResource(UserService userService, RoleService roleService, TokenService tokenService) {
        this.userService = userService;
        this.roleService = roleService;
        this.tokenService = tokenService;
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder ver =
                new GoogleIdTokenVerifier.Builder(transport, factory)
                        .setAudience(Collections.singleton(IdClient));
        GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(), tokenDTO.getToken());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        String email = payload.getEmail();
        User user = new User();
        if(userService.ifEmailExist(email)) {
            user = userService.getUserByMail(email);
        } else {
            user = createUser(email);
        }
        return new ResponseEntity<>(payload, OK);
    }

    private User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        List<Role> roles = roleService.getRoles();
        user.getRoles().add(roles.get(0));
        return userService.saveUser(user);
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody TokenDTO tokenDTO) {
        Facebook facebook = new FacebookTemplate(tokenDTO.getToken());
        String[] data = { "email", "name", "picture" };
        User user = facebook.fetchObject("me", User.class, data);
        return new ResponseEntity<>(user, OK);
    }
}
