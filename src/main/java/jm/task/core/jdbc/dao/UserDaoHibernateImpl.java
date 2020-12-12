package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = util.getSessionFactory();
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT,\n" +
                "name VARCHAR (20) NOT NULL,\n" +
                "lastname VARCHAR (25) NOT NULL,\n" +
                "age SMALLINT ,\n" +
                "primary key (id))";
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = util.getSessionFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        SessionFactory sessionFactory = util.getSessionFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем - " + user.getName() + " добавлен в таблицу");
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sessionFactory.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = util.getSessionFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = (User) session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sessionFactory.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = util.getSessionFactory();
        List<User> users = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sessionFactory.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = util.getSessionFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            sessionFactory.close();
        }
    }
}
