package com.ey.Client;

import com.ey.models.AuthorizeForm;
import com.ey.models.BankAccount;
import com.ey.models.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "useraccount")
public interface UserAccountClient {

    @GetMapping
    public List<UserAccount> findByIds(@RequestParam List<Integer> ids);

    @PostMapping("/useraccounts/verify")
    public UserAccount getUserAccountByLogin(@RequestBody AuthorizeForm authorizeForm);

}
