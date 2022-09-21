package com.ey.repositories;

import com.ey.models.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends CrudRepository <BankAccount, Integer> {

}
