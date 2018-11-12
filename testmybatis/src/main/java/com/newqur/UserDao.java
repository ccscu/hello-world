package com.newqur;

import java.util.List;

public interface UserDao {
    /**
     * find user by id
     * @param id id
     * @return user
     * @throws Exception err
     */
    public User findUserById(int id) throws Exception ;
    public List<User> findAllUsers() throws Exception;
    public void insertUser(User user) throws Exception;
    public void deleteUserById(int id) throws Exception;
    public void updateUserPassword(User user) throws Exception;
}
