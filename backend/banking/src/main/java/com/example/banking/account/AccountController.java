package com.example.banking.account;

import com.example.banking.base.BankingApplicationController;
import com.example.banking.base.ControllerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/accounts")
@CrossOrigin(origins = "*")

public class AccountController extends BankingApplicationController {

    private final AccountService accountService;
    private ControllerStatus controllerStatus;

    @Autowired
    public AccountController(AccountService accountService){
        super(ControllerStatus.ONLINE);
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping
    public void registerNewAccount(@RequestBody Account account){
        accountService.addNewAccount(account);
    }

    @PutMapping (path = "{accountId}")
    public void updateAccount(@PathVariable("accountId") Long accountId,
                              @RequestParam int maxDeposit){
        accountService.updateAccount(accountId, maxDeposit);
    }

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId){
        accountService.deleteAccount(accountId);
    }

    @PostMapping(path = "transfer")
    public void transferFunds(@RequestParam("sourceAccountId") Long sourceAccountId,
                              @RequestParam("destAccountId") Long destAccountId,
                              @RequestParam("transferAmountCents") int transferAmountCents
                              ){
        accountService.transferFunds(sourceAccountId, destAccountId, transferAmountCents);
    }

    @GetMapping(params = "ownerId")
    public HashMap<String, List<Account>> getAccountsOwnedByUser(@RequestParam(required = true) Long ownerId){
        return accountService.getAccountsOwnedByUser(ownerId);
    }

}
