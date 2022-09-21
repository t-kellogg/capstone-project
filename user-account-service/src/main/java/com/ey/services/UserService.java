package com.ey.services;

import com.ey.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers(Integer[] ids);
    public Optional<User> getUserById(int id);
    public User addUser(User user);
    public User updateUser(User user);
    public boolean deleteUser(int id);

}
