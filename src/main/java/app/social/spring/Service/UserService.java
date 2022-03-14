package app.social.spring.Service;

import app.social.spring.DAO.UserDAO;
import app.social.spring.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDetails loadByEmail(String email ) {
        User user = userDAO.findByEmail(email);
    }
}
