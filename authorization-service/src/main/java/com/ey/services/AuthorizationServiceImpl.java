package com.ey.services;

import com.ey.models.UserAccountsBankAccounts;
import com.ey.repositories.UserAccountBankAccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
    UserAccountBankAccountsRepo userAccountBankAccountsRepo;

    @Override
    public boolean checkAuthorization(int userAccountId, int bankAccountId) {
        UserAccountsBankAccounts userAccountsBankAccounts = userAccountBankAccountsRepo.findByUserAccountIdAndBankAccountId(userAccountId, bankAccountId);
        if(isNull(userAccountsBankAccounts))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
