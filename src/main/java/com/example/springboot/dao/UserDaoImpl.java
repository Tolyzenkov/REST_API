package com.example.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RoleDao roleDao;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user, List<String> roles)
    {
        user.setRoles(roleDao.setupRoles(user, roles));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createNamedQuery("User.getAll", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        return getAllUsers().stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return getAllUsers().stream().filter(user -> user.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void updateUser(User user, long id, List<String> role) {
        user.setRoles(roleDao.setupRoles(user, role));
        getUserById(id).setName(user.getName());
        getUserById(id).setSurname(user.getSurname());
        getUserById(id).setEmail(user.getEmail());
        getUserById(id).setPassword(user.getPassword());
        getUserById(id).setRoles(user.getRoles());
        entityManager.refresh(getUserById(id));
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }
}
