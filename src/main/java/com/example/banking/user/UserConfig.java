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
                    LocalDate.of(2001, NOVEMBER, 12));
            User cindy = new User("Cindy",
                    "Pratt",
                    "cprat0404@ymail.com",
                    LocalDate.of(1992, OCTOBER, 3));

            repository.saveAll(List.of(dennis, cindy));

        };
    }
}
