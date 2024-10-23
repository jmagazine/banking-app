package com.example.banking.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository){
        return args -> {
            User dennis = new User("Dennis",
                    "Ethan",
                    "dethan03@gmail.com",
                    "d_eth03",
                    "abcdefg",
                    LocalDate.of(2001, NOVEMBER, 12));
            User cindy = new User("Cindy",
                    "Pratt",
                    "cpratt0404@ymail.com",
                    "cpratt0404",
                    "helloworld",
                    LocalDate.of(1992, OCTOBER, 3));

            repository.saveAll(List.of(dennis, cindy));

        };
    }
}
