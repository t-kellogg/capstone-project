package com.ey.controller;

import com.ey.models.AuthenticationForm;
import com.ey.models.UserAccount;
import com.ey.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<Boolean> checkAuthentication(@RequestBody AuthenticationForm authenticationForm) {
        boolean isAuthenticated = authenticationService.checkAuthentication(authenticationForm.getUsername(), authenticationForm.getToken());
        return ResponseEntity.ok(isAuthenticated);
    }

}
