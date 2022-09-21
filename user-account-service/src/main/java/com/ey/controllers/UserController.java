package com.ey.controllers;

import com.ey.models.User;
import com.ey.services.UserService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService us;

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
    public ResponseEntity<List<User>> getAllUsers() {
         List<User> users = us.getAllUsers();
//         if (ids == null){
//             return ResponseEntity.ok(users);
//         }
//         if(users.size() != ids.length){
//             return ResponseEntity.badRequest().body(users);
//         }
         return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> optional = us.getUserById(id);
        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping // DO we need messaging and event handling??
    public ResponseEntity<User> addUser(@RequestBody User user) {

        user = us.addUser(user);
//        UserEvent event = new UserEvent(Operation.CREATE, user);
//        messagingService.triggerEvent(event);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {

        user.setId(id);
        user = us.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {

        Optional<User> optional = us.getUserById(id);

        if(optional.isPresent()) {
            boolean wasDeleted = us.deleteUser(id);
//            UserEvent event = new UserEvent(Operation.DELETE, f);
//            messagingService.triggerEvent(event);
            if(wasDeleted) {
                return ResponseEntity.ok(optional.get());
            }else{
                return (ResponseEntity<User>) ResponseEntity.badRequest();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
