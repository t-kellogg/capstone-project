package com.ey.controllers;

import com.ey.Client.AuthenticationClient;
import com.ey.Client.AuthorizationClient;
import com.ey.Client.UserAccountClient;
import com.ey.models.*;
import com.ey.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bankaccounts")
public class BankController {

    @Autowired
    BankService bankService;

    @Autowired
    AuthenticationClient authenticationClient;
    @Autowired
    AuthorizationClient authorizationClient;

    @Autowired
    UserAccountClient userAccountClient;


    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = (List<BankAccount>)bankService.getAllBankAccounts();

        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bankService.getBankAccount(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount newBankAccount = bankService.addBankAccount(bankAccount);
        return ResponseEntity.status(201).body(newBankAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String id) {
        bankAccount.setId(Integer.parseInt(id));
        BankAccount updatedBankAccount = bankService.updateBankAccount(bankAccount);
        if(updatedBankAccount != null) {
            return ResponseEntity.ok(updatedBankAccount);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankAccount> deleteBankAccount(@PathVariable String id) {

        Optional<BankAccount> bankAccountOptional = Optional.of(bankService.getBankAccount(Integer.parseInt(id)));
        if(bankAccountOptional.isPresent()) {
            boolean deleted = bankService.deleteBankAccount(Integer.parseInt(id));
            if(deleted)
                return ResponseEntity.ok(bankAccountOptional.get());
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Log> performTransaction(@RequestBody TransactionForm transactionForm){

        AuthenticationForm authenticationForm = new AuthenticationForm(transactionForm.getUsername(), transactionForm.getToken());
        Boolean authenticated = authorizationClient.checkAuthentication(authenticationForm);

        if(!authenticated) {
            return ResponseEntity.status(403).build();
        }

       // Must authorize frombank and id if transfer or withdrawal
        Action action = transactionForm.getAction();
        if(action == Action.WITHDRAW || action == Action.TRANSFER) {
            int id = getUserId(transactionForm.getUsername(), transactionForm.getToken());
            AuthorizationForm authorizationForm = new AuthorizationForm(id, transactionForm.getFromBankId());
            Boolean authorized = authorizationClient.checkAuthorization(authorizationForm);
            if(!authorized) {
                return ResponseEntity.status(403).build();
            }
        }

        if(transactionForm.getAction().equals(Action.WITHDRAW)){
            Log log = bankService.performWithdraw(transactionForm);
            if(log != null){
                return ResponseEntity.ok(log);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else if(transactionForm.getAction().equals(Action.DEPOSIT)){
            Log log = bankService.performDeposit(transactionForm);
            if(log != null){
                return ResponseEntity.ok(log);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else if(transactionForm.getAction().equals(Action.TRANSFER)){
            Log log = bankService.performTransfer(transactionForm);
            if(log != null){
                return ResponseEntity.ok(log);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    public int getUserId(String username, String token) {
        AuthorizeForm authorizeForm = new AuthorizeForm(username, token);
        UserAccount userAccount = userAccountClient.getUserAccountByLogin(authorizeForm);
        return userAccount.getId();
    }


}
