package com.ey.clients;

import com.ey.models.BankAccount;
import com.ey.models.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "useraccount")
public interface UserAccountClient {

    @GetMapping("/useraccounts")
    public List<UserAccount> findByIds(@RequestParam List<Integer> ids);
}
