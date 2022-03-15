package app.social.spring.Service;

import app.social.spring.DAO.UserDAO;
import app.social.spring.Entity.User;
import app.social.spring.Utility.Config.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }

    public boolean ifEmailExist(String email) {
        return userDAO.existsByEmail(email);
    }
}
