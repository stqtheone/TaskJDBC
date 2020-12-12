package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        UserServiceImpl userService = new UserServiceImpl();
        User user1 = new User("sasha","gubanov",(byte) 23);
        User user2 = new User("vladimir","ovechkin",(byte) 20);
        User user3 = new User("darya","vostrova",(byte) 25);
        User user4 = new User("mitya","kolinin",(byte) 40);
        userService.createUsersTable();
        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        userService.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        userService.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
