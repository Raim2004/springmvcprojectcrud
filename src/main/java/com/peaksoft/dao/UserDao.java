package com.peaksoft.dao;

import com.peaksoft.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void removeUser(User user);

    User getById(Long id);

    User getByUsername(String username);
}
