package com.ey.repositories;

import com.ey.models.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Integer> {

}
