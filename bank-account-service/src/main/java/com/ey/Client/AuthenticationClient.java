package com.ey.Client;

import com.ey.models.AuthenticationForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication")
public interface AuthenticationClient {

    @GetMapping("/authorization/authentication")
    public boolean checkAuthentication(@RequestBody AuthenticationForm authenticationForm);


}
