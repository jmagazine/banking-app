package com.example.banking.status;

import com.example.banking.account.AccountController;
import com.example.banking.account.investment.InvestmentController;
import com.example.banking.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path ="api/v1/status")
public class StatusController {
    private final UserController userController;
    private final AccountController accountController;
    private final InvestmentController investmentController;
    private final HashMap<String, String> statuses = new HashMap<>();

    @Autowired
    public StatusController(UserController userController, AccountController accountController, InvestmentController investmentController){
        this.userController = userController;
        statuses.put("userController", "ONLINE");
        this.accountController = accountController;
        statuses.put("accountController", "ONLINE");
        this.investmentController = investmentController;
        statuses.put("investmentController", "ONLINE");
    }

    @GetMapping
    public HashMap<String, String> getStatuses(){
        return this.statuses;
    }
}
