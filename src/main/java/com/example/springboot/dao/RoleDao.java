package com.example.springboot.dao;

import org.springframework.stereotype.Component;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import java.util.List;
import java.util.Set;

@Component
public interface RoleDao {
    Role getRoleByRolename(String rolename);
    List<Role> rolesList();
    Set<Role> setupRoles(User user, List<String> roleAdmin);


}
