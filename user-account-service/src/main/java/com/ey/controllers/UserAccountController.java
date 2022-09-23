package com.ey.controllers;

import com.ey.clients.BankAccountClient;
import com.ey.models.BankAccount;
import com.ey.models.UserAccount;
import com.ey.services.UserAccountService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("useraccounts")
public class UserAccountController {

    @Autowired
    private UserAccountService us;

    @Autowired
    private BankAccountClient bankAccountClient;

//    @Autowired
//    private Environment env;

//    @Autowired
//    private MessagingService messagingService;
//
//    @GetMapping("/port")
//    public String getPort(){
//        String port = env.getProperty("local.server.port");
//        return "Response from port: " + port;
//    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUserAccounts() {
        List<UserAccount> userAccounts = us.getAllUsers();
        return ResponseEntity.ok(userAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccountById(@PathVariable int id) {
        Optional<UserAccount> optional = us.getUserById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping // DO we need messaging and event handling??
    public ResponseEntity<UserAccount> addUserAccount(@RequestBody UserAccount userAccount) {

        userAccount = us.addUser(userAccount);
//        UserEvent event = new UserEvent(Operation.CREATE, user);
//        messagingService.triggerEvent(event);
        return ResponseEntity.status(201).body(userAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable int id) {

        userAccount.setId(id);
        userAccount = us.updateUser(userAccount);
        return ResponseEntity.ok(userAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserAccount> deleteUserAccount(@PathVariable int id) {

        Optional<UserAccount> userAccount = us.getUserById(id);
        if (!userAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Integer> ids = userAccount.get().getBankAccounts();
        int i = 0;

        if (ids.size()!=0) {
            List<BankAccount> bankAccounts = bankAccountClient.findByIds(ids);

            for (BankAccount ba : bankAccounts) {
                if (ba.getBalance() < 0) {
                    i = 1;
                    break;
                }
            }
        }

        if (i == 0) {
            userAccount.get().setActive(false);
            us.updateUser(userAccount.get());
            return ResponseEntity.ok(userAccount.get());
        } else {
            return (ResponseEntity<UserAccount>) ResponseEntity.badRequest();
        }

    }

}
