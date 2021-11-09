package com.peaksoft.dao;

import com.peaksoft.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().
                createQuery("from User").
                getResultList();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.contains(user) ? user : session.merge(user));
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class,id);

    }

    @Override
    public User getByUsername(String username) {
        List<User> users = getAllUsers();
        return users.stream().filter(x -> x.getName().equals(username)).findAny().orElse(new User());
    }
}
