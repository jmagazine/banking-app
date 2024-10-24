package com.example.banking.account;

import com.example.banking.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("CHECKINGS")
public class CheckingAccount extends Account {
    // Overdraft limit in cents.
    private int overdraftLimit;

    public CheckingAccount() {
    }

    public CheckingAccount(long ownerId, int balanceCents, LocalDate creationDate, int maxDeposit, int overdraftLimit) {
        super(ownerId, balanceCents, creationDate, maxDeposit);
        this.overdraftLimit = overdraftLimit;
    }

    public int getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(int overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

}
