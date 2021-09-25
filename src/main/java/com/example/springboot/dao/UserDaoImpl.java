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
    private final UsrDao usrDao;

    private final RoleDao roleDao;

    @Autowired
    public UserDaoImpl(UsrDao usrDao, RoleDao roleDao) {
        this.usrDao = usrDao;
        this.roleDao = roleDao;
    }

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user)
    {
//        user.setRoles(user.getRoles());
      usrDao.save(user);
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
    public void updateUser(User user) {
        System.out.println(user.getName());
        getUserById(user.getId()).setName(user.getName());
        getUserById(user.getId()).setSurname(user.getSurname());
        getUserById(user.getId()).setEmail(user.getEmail());
        getUserById(user.getId()).setPassword(user.getPassword());
        getUserById(user.getId()).setRoles(user.getRoles());
        entityManager.refresh(getUserById(user.getId()));
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }
}
