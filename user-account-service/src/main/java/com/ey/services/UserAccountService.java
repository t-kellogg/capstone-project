package com.ey.services;

import com.ey.models.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {

    public List<UserAccount> getAllUsers();
    public Optional<UserAccount> getUserById(int id);
    public UserAccount addUser(UserAccount userAccount);
    public UserAccount updateUser(UserAccount userAccount);
    public boolean deleteUser(int id);

    public UserAccount getUserByUsernameAndToken(String username, String token);

}
