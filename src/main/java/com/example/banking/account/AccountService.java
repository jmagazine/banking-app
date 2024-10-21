package com.example.banking.account;

import com.example.banking.user.User;
import com.example.banking.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class AccountService {
    public AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }


    public void addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        if (accountOptional.isPresent()){
            throw new IllegalStateException(
                    String.format("Account with id %d already exists", account.getId()));
        }
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){
        boolean exists = accountRepository.existsById(accountId);
        if (!exists){
            throw new IllegalStateException(String.format("Account with id %d already exists", accountId));
        }
        accountRepository.deleteById(accountId);
    }

    public void updateAccount(Long accountId, int maxDeposit){
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()){
            throw new IllegalStateException("An account with id %d does not exist.");
        }
        Account account = accountOptional.get();
        // maxDeposit should be the only feature of an account that should be modifiable via
        // this method
        if (maxDeposit > 0 && maxDeposit != account.getMaxDeposit()){
            account.setMaxDeposit(maxDeposit);
        }
    }
}

