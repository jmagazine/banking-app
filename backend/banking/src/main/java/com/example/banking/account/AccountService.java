package com.example.banking.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }


    @Transactional
    public void addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        if (accountOptional.isPresent()){
            throw new IllegalStateException(
                    String.format("Account with id %d already exists", account.getId()));
        }
        accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(Long accountId){
        boolean exists = accountRepository.existsById(accountId);
        if (!exists){
            throw new IllegalStateException(String.format("Account with id %d already exists", accountId));
        }
        accountRepository.deleteById(accountId);
    }

    @Transactional
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

    @Transactional
    public void transferFunds(Long sourceAccountId, Long destAccountId, int transferAmountCents) {
        Optional<Account> sourceAccountOptional = accountRepository.findById(sourceAccountId);
        Optional<Account> destAccountOptional = accountRepository.findById(destAccountId);
        if (sourceAccountOptional.isEmpty() || destAccountOptional.isEmpty()){
            throw new IllegalStateException("Invalid source/destination account ID.");
        }
        Account sourceAccount = sourceAccountOptional.get();
        Account destAccount = destAccountOptional.get();
        if (sourceAccount.getBalance() < transferAmountCents){
            throw new IllegalStateException("Transfer amount exceeds balance in source account.");
        }
        int oldSourceBalance = sourceAccount.getBalance();
        int oldDestBalance = destAccount.getBalance();
        sourceAccount.withdraw(transferAmountCents);
        destAccount.deposit(transferAmountCents);

        if (oldDestBalance + transferAmountCents != destAccount.getBalance() || sourceAccount.getBalance() + transferAmountCents != oldSourceBalance)
        {
            throw new IllegalStateException("Balances are not consistent. Reverting transfer.");
        }

    }
}

