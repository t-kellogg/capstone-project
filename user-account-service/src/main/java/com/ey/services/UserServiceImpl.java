package com.ey.services;

import com.ey.models.User;
import com.ey.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo ur;

    @Autowired
    private Environment env;

    @Override
    public List<User> getAllUsers(@RequestParam(required = false) Integer[] ids) {
        if(ids == null) {
            return (List<User>) ur.findAll();
        }
        return (List<User>) ur.findAllById(Arrays.asList(ids));
    }

    @Override
    public Optional<User> getUserById(int id) {
        return ur.findById(id);
    }

    @Override
    public User addUser(User user) {
        return ur.save(user);
    }

    @Override
    public User updateUser(User user) {
        return ur.save(user);
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            ur.deleteById(id);
            return true;
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }
}
