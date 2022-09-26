package com.ey.services;

import com.ey.models.UserAccount;
import com.ey.repositories.UserAccountRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepo ur;

    @Autowired
    private Environment env;

    @Override
    public List<UserAccount> getAllUsers() {
//        if(ids == null) {
            List<UserAccount> userAccounts = (List<UserAccount>) ur.findAll();
            System.out.println(userAccounts);
            return userAccounts;
//        }
//        return (List<User>) ur.findAllById(Arrays.asList(ids));
    }

    @Override
    public Optional<UserAccount> getUserById(int id) {
        return ur.findById(id);
    }

    @Override
    public UserAccount addUser(UserAccount userAccount) {
        return ur.save(userAccount);
    }

    @Override
    public UserAccount updateUser(UserAccount userAccount) {
        return ur.save(userAccount);
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

    @Override
    public UserAccount getUserByUsernameAndToken(String username, String token) {
        Optional<UserAccount> userAccountOptional = ur.findByUsernameAndToken(username, token);
        return userAccountOptional.get();
    }
}
