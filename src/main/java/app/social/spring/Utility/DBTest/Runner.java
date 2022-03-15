package app.social.spring.Utility.DBTest;

import app.social.spring.DAO.UserDAO;
import app.social.spring.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        user.setPassword("1234abcd");
        userDAO.save(user);

        User user1 = new User();
        user.setEmail("test1@test.com");
        user.setPassword("1234abcd1");
        userDAO.save(user1);
    }
}
