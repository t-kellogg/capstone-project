package com.ey.services;

import com.ey.models.BankAccount;
import com.ey.models.Log;
import com.ey.models.TransactionForm;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface BankService {

    public BankAccount addBankAccount(BankAccount bankAccount);
    public BankAccount getBankAccount(int id);
    public List<BankAccount> getAllBankAccounts();
    public BankAccount updateBankAccount(BankAccount newBankAccount);
    public boolean deleteBankAccount(int id) throws EmptyResultDataAccessException;
    Log performWithdraw(TransactionForm transactionForm);
    Log performDeposit(TransactionForm transactionForm);
    Log performTransfer(TransactionForm transactionForm);

}
