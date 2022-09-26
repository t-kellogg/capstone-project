package com.ey.Client;

import com.ey.models.AuthenticationForm;
import com.ey.models.UserAccount;
import com.ey.models.AuthorizationForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "authorization")
public interface AuthorizationClient {

    @PostMapping("/auth/authorization")
    public boolean checkAuthorization(@RequestBody AuthorizationForm authorizationForm);

    @PostMapping("/auth/authentication")
    public boolean checkAuthentication(@RequestBody AuthenticationForm authenticationForm);


}
