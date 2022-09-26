package com.ey.controller;

import com.ey.models.AuthenticationForm;
import com.ey.models.UserAccount;
import com.ey.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Boolean> checkAuthentication(@RequestBody AuthenticationForm authenticationForm) {
        boolean isAuthenticated = authenticationService.checkAuthentication(authenticationForm.getUsername(), authenticationForm.getToken());
        return ResponseEntity.ok(isAuthenticated);
    }

}
