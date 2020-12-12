package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args){
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("sasha","gubanov",(byte) 22);
        userDaoHibernate.saveUser("tanya","gubanova",(byte) 23);
        userDaoHibernate.saveUser("zhenya","gubanov",(byte) 24);
        userDaoHibernate.saveUser("valeriy","gubanov",(byte) 25);
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();




    }
}
