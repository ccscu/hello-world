package com.newqur;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class AppTest 
{
    @Test
    public void testFindUserById() throws Exception{
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUserById(3);
        System.out.println(user);
    }
    @Test
    public void testFindAllUser() throws Exception{
        UserDao userDao = new UserDaoImpl();
        List<User> findAllUsers = userDao.findAllUsers();
        for (User user : findAllUsers) {
            System.out.println(user);
        }
    }
    @Test
    public void testInsertUser() throws Exception{
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setUsername("张三");
        user.setPassword("lalal");
        user.setAge(12);
        userDao.insertUser(user);
    }
    @Test
    public void testDeleteUserById() throws Exception{
        UserDao userDao = new UserDaoImpl();
        userDao.deleteUserById(2);
    }
    @Test
    public void testUpdateUserPassword() throws Exception{
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setId(1);
        user.setPassword("newpassword");
        userDao.updateUserPassword(user);
    }
}
