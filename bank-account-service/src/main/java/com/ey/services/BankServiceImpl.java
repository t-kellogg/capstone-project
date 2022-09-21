package com.ey.services;

import com.ey.models.BankAccount;
import com.ey.repositories.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    BankRepo bankRepo;

    @Override
    public BankAccount addBankAccount(BankAccount bankAccount) {
        return bankRepo.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccount(int id) {
        Optional<BankAccount> optionalBankAccount= bankRepo.findById(id);
        return optionalBankAccount.orElseGet(null);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return (List<BankAccount>)bankRepo.findAll();
    }

    @Override
    public BankAccount updateBankAccount(BankAccount newBankAccount) {
        return bankRepo.save(newBankAccount);
    }

    @Override
    public boolean deleteBankAccount(int id) throws EmptyResultDataAccessException {
        try {
            bankRepo.deleteById(id);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
