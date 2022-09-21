package com.ey.controllers;

import com.ey.models.BankAccount;
import com.ey.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/bankaccounts")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        return ResponseEntity.ok(bankService.getAllBankAccounts());
    }

    @GetMapping("/bankaccounts/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bankService.getBankAccount(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount newBankAccount = bankService.addBankAccount(bankAccount);
        return ResponseEntity.status(201).body(newBankAccount);
    }

    @PutMapping("/bankaccounts/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String id) {
        bankAccount.setId(Integer.parseInt(id));
        BankAccount updatedBankAccount = bankService.updateBankAccount(bankAccount);
        if(updatedBankAccount != null) {
            return ResponseEntity.ok(updatedBankAccount);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/bankaccounts/{id}")
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


}
