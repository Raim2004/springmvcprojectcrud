package com.peaksoft.service;


import com.peaksoft.model.User;

import java.util.List;


public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void removeUser(User user);

    User getById(Long id);
}
