package com.ey.services;

import org.springframework.stereotype.Service;


public interface AuthorizationService {
    public boolean checkAuthorization(int userAccountId, int bankAccountId);
}
