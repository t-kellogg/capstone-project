package com.ey.controller;



import com.ey.models.AuthenticationForm;
import com.ey.models.AuthorizationForm;
import com.ey.services.AuthenticationService;
import com.ey.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<Boolean> checkAuthorization(@RequestBody AuthorizationForm authorizationForm) {
        boolean isAuthenticated = authorizationService.checkAuthorization(authorizationForm.getUserAccountId(), authorizationForm.getBankAccountId());
        return ResponseEntity.ok(isAuthenticated);
    }

}
