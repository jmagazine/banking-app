package com.example.banking.account;

import com.example.banking.user.User;
import com.example.banking.user.UserRepository;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner accountCommandLineRunner(AccountRepository accountRepository, UserRepository userRepository){
        return args -> {
            User owner = new User(
                    "Peter",
                    "Griffin",
                    "petey33@aol.com",
                    LocalDate.of(2000, Month.JANUARY, 12)
            );
            userRepository.save(owner);
            Account account1 = new CheckingAccount(owner, 0, LocalDate.now(), 10000*100, 250*100);
            Account account2 = new SavingsAccount(owner, 100*100, LocalDate.now(), 10000*100, 0.05);
            accountRepository.saveAll(List.of(account1, account2));
        };
    }


}
