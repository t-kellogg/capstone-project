package com.ey.clients;

import com.ey.models.AuthenticationForm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authorization")
public interface AuthorizationClient {

//    @PostMapping("/auth/authorization")
//    public boolean checkAuthorization(@RequestBody AuthorizationForm authorizationForm);

    @PostMapping("/auth/authentication")
    public boolean checkAuthentication(@RequestBody AuthenticationForm authenticationForm);


}
