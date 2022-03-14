package app.social.spring.API;

import app.social.spring.DTO.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GoogleResource {

    @Value("${google.id}")
    private String IdClient;

    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDTO tokenDTO) {

    }
}
