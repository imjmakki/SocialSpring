package app.social.spring.API;

import app.social.spring.DTO.TokenDTO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SocialResource {

    @Value("${google.id}")
    private String IdClient;

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder ver =
                new GoogleIdTokenVerifier.Builder(transport, factory)
                        .setAudience(Collections.singleton(IdClient));
        GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(), tokenDTO.getToken());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        return new ResponseEntity<>(payload, OK);
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody TokenDTO tokenDTO) {
    }
}
