package app.social.spring.Utility.DBTest;

import app.social.spring.DAO.UserDAO;
import app.social.spring.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Runner implements CommandLineRunner {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Runner(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword(passwordEncoder.encode("test1234"));
        userDAO.save(user);

        User user1 = new User();
        user1.setEmail("test1@test.com");
        user1.setPassword(passwordEncoder.encode("test1234"));
        userDAO.save(user1);
    }
}
