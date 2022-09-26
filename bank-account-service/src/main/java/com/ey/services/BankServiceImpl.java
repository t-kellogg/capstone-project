package com.ey.services;

import com.ey.models.Action;
import com.ey.models.BankAccount;
import com.ey.models.Log;
import com.ey.models.TransactionForm;
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

    @Autowired
    LogService ls;

    @Override
    public BankAccount addBankAccount(BankAccount bankAccount) {
        if(bankAccount.getBalance() >= 100) {
            return bankRepo.save(bankAccount);
        }else{
            return null;
        }
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

    @Override
    public Log performWithdraw(TransactionForm transactionForm) {
        // Authorization
        Optional<BankAccount> bankAccount = bankRepo.findById(transactionForm.getFromBankId());
        if(bankAccount.isEmpty()) return null;
        Float newBalance = getNewBalance(transactionForm, bankAccount);
        BankAccount afterTransactionBankAccount = convertToBankAccount(transactionForm.getFromBankId(),newBalance);
        bankRepo.save(afterTransactionBankAccount);
        Log log = createLog(transactionForm);
        ls.addLog(log);
        return log;
    }

    @Override
    public Log performDeposit(TransactionForm transactionForm) {
        // Authorization
        Optional<BankAccount> bankAccount = bankRepo.findById(transactionForm.getToBankId());
        if(bankAccount.isEmpty()) return null;
        Float newBalance = getNewBalance(transactionForm, bankAccount);
        BankAccount afterTransactionBankAccount = convertToBankAccount(transactionForm.getToBankId(),newBalance);
        bankRepo.save(afterTransactionBankAccount);
        Log log = createLog(transactionForm);
        ls.addLog(log);
        return log;

    }

    @Override
    public Log performTransfer(TransactionForm transactionForm) {

        //Auth
        //Withdraw
        transactionForm.setAction(Action.WITHDRAW);
        Optional<BankAccount> fromBankAccount = bankRepo.findById(transactionForm.getFromBankId());
        if(fromBankAccount.isEmpty()) return null;
        Float fromNewBalance = getNewBalance(transactionForm, fromBankAccount);
        BankAccount fromTransactionBankAccount = convertToBankAccount(transactionForm.getFromBankId(),fromNewBalance);
        bankRepo.save(fromTransactionBankAccount);

        // Deposit
        transactionForm.setAction(Action.DEPOSIT);
        Optional<BankAccount> toBankAccount = bankRepo.findById(transactionForm.getToBankId());
        if(toBankAccount.isEmpty()) return null;
        Float ToNewBalance = getNewBalance(transactionForm, toBankAccount);
        BankAccount toTransactionBankAccount = convertToBankAccount(transactionForm.getToBankId(),ToNewBalance);
        bankRepo.save(toTransactionBankAccount);

        transactionForm.setAction(Action.TRANSFER);

        // Add log to the log table
        Log log = createLog(transactionForm);
        ls.addLog(log);
        return log;
    }

    private Float getNewBalance(TransactionForm transactionForm, Optional<BankAccount> fromBankAccount) {
        float fromNewBalance = 0;
        float currentBalance = fromBankAccount.get().getBalance();

        Action action = transactionForm.getAction();

        if(action == Action.DEPOSIT) {
            if(transactionForm.getTransactionAmount() > 0) {
                BankAccount toBankAccount = getBankAccount(transactionForm.getToBankId());
                fromNewBalance = toBankAccount.getBalance() + transactionForm.getTransactionAmount();
            }
        }
        else if(action == Action.WITHDRAW) {
            if(transactionForm.getTransactionAmount() > 0) {
                fromNewBalance = currentBalance - transactionForm.getTransactionAmount();
            }
        }

        return fromNewBalance;
    }

    private BankAccount convertToBankAccount(int bankId, float balance){
        BankAccount bankAccount = getBankAccount(bankId);
        bankAccount.setBalance(balance);
        return bankAccount;
    }

    private Log createLog(TransactionForm transactionForm){
        Log log = new Log();
        log.setAction(transactionForm.getAction());
        log.setAmount(transactionForm.getTransactionAmount());
        log.setTimestamp(System.currentTimeMillis());
        Optional<BankAccount> toBankAccount = bankRepo.findById(transactionForm.getToBankId());
        toBankAccount.ifPresent(log::setToBank);
        Optional<BankAccount> fromBankAccount = bankRepo.findById((transactionForm.getFromBankId()));
        fromBankAccount.ifPresent(log::setFromBank);



        return log;
    }

}
