package com.ey.clients;

import com.ey.models.BankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "bankaccount")
public interface BankAccountClient {

    @GetMapping("/bankaccounts")
    public List<BankAccount> findByIds(@RequestParam List<Integer> ids);
}
