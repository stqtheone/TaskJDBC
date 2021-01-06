package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    SessionFactory sessionFactory = util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT,\n" +
                "name VARCHAR (20) NOT NULL,\n" +
                "lastname VARCHAR (25) NOT NULL,\n" +
                "age SMALLINT ,\n" +
                "primary key (id))";
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.save(user);
            System.out.println("User с именем - " + user.getName() + " добавлен в таблицу");
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = (User) session.get(User.class,id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            users = session.createQuery("from User").list();
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();

        } finally {
            session.close();
        }
    }
}
