package app.social.spring.DTO;

import lombok.Data;

@Data
public class JwtLogin {

    private String email;
    private String password;
}
