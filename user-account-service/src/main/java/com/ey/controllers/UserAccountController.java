package com.ey.controllers;

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
    private Environment env;

//    @Autowired
//    private MessagingService messagingService;

    @GetMapping("/port")
    public String getPort(){
        String port = env.getProperty("local.server.port");
        return "Response from port: " + port;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        System.out.println("Here");
         List<UserAccount> userAccounts = us.getAllUsers();
//         if (ids == null){
//             return ResponseEntity.ok(users);
//         }
//         if(users.size() != ids.length){
//             return ResponseEntity.badRequest().body(users);
//         }
        System.out.println(userAccounts);
         return ResponseEntity.ok(userAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserById(@PathVariable int id) {
        Optional<UserAccount> optional = us.getUserById(id);
        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping // DO we need messaging and event handling??
    public ResponseEntity<UserAccount> addUser(@RequestBody UserAccount userAccount) {

        userAccount = us.addUser(userAccount);
//        UserEvent event = new UserEvent(Operation.CREATE, user);
//        messagingService.triggerEvent(event);
        return ResponseEntity.status(201).body(userAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> updateUser(@RequestBody UserAccount userAccount, @PathVariable int id) {

        userAccount.setId(id);
        userAccount = us.updateUser(userAccount);
        return ResponseEntity.ok(userAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserAccount> deleteUser(@PathVariable int id) {

        Optional<UserAccount> optional = us.getUserById(id);

        if(optional.isPresent()) {
            boolean wasDeleted = us.deleteUser(id);
//            UserEvent event = new UserEvent(Operation.DELETE, f);
//            messagingService.triggerEvent(event);
            if(wasDeleted) {
                return ResponseEntity.ok(optional.get());
            }else{
                return (ResponseEntity<UserAccount>) ResponseEntity.badRequest();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
