package app.social.spring.Service;

import app.social.spring.DAO.RoleDAO;
import app.social.spring.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public List<Role> getRoles() {
        return roleDAO.findAll();
    }
}
