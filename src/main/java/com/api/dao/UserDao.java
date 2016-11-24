package com.api.dao;

import com.api.domain.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

public class UserDao extends AbstractDAO<User>{

    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> find(String username, String password) {
        Session session = sessionFactory.openSession();
        Criteria criteria =  session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", username));
        criteria.add(Restrictions.eq("password", password));
        Object value = criteria.uniqueResult();
        Optional<User> user = Optional.ofNullable((User) value);
        session.close();
        return user;
    }

    public User save(User user) {
        return super.persist(user);
    }
}
