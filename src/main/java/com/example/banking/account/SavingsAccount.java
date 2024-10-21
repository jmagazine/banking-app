package com.example.banking.account;

import com.example.banking.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount() {
    }

    public SavingsAccount(long id, User owner, int balanceCents, LocalDate creationDate, int maxDeposit, double interestRate) {
        super(id, owner, balanceCents, creationDate, maxDeposit);
        this.interestRate = interestRate;
    }

    public SavingsAccount(User owner, int balanceCents, LocalDate creationDate, int maxDeposit, double interestRate) {
        super(owner, balanceCents, creationDate, maxDeposit);
        this.interestRate = interestRate;
    }

}
