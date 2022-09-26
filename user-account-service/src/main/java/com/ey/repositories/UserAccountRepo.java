package com.ey.repositories;

import com.ey.models.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Integer> {
    public Optional<UserAccount> findByUsernameAndToken(String username, String token);

}
