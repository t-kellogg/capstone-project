package com.ey.services;

import com.ey.models.UserAccount;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    public boolean checkAuthentication(String username, String token);
}
