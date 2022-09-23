package com.ey.services;

import com.ey.models.UserAccount;
import com.ey.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    UserAccountRepo userAccountRepo;

    @Override
    public boolean checkAuthentication(String username, String token) {
        UserAccount userAccount = userAccountRepo.findByUsernameAndToken(username, token);
        if(isNull(userAccount))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
