package com.ey.repositories;

import com.ey.models.UserAccountsBankAccounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountBankAccountsRepo extends CrudRepository<UserAccountsBankAccounts, Integer> {
    UserAccountsBankAccounts findByUserAccountIdAndBankAccountId(int userAccountId, int bankAccountId);
}
