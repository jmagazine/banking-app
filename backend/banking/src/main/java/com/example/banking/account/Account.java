package com.example.banking.account;

import com.example.banking.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="accounts")
public abstract class Account {
    @Id
    @SequenceGenerator(name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "account_sequence")
    private long id;
    private long ownerId;
    private int balanceCents;
    private LocalDate creationDate;

    private int maxDeposit;

    public Account(long id, long ownerId, int balanceCents, LocalDate creationDate, int maxDeposit) {
        this.id = id;
        this.ownerId = ownerId;
        this.balanceCents = balanceCents;
        this.creationDate = creationDate;
        this.maxDeposit = maxDeposit;
    }

    public Account(long ownerId, int balanceCents, LocalDate creationDate, int maxDeposit) {
        this.ownerId = ownerId;
        this.balanceCents = balanceCents;
        this.creationDate = creationDate;
        this.maxDeposit = maxDeposit;

    }


    public Account() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public int getBalance() {
        return balanceCents;
    }

    public void deposit(int cents) {
        if (cents < 0 || cents > maxDeposit ){
            throw new IllegalStateException(String.format("Invalid argument %d for cents.", cents));
        }
        this.balanceCents += cents;
    }

    public void withdraw(int cents){
        if (cents < 0 || cents > balanceCents){
            throw new IllegalStateException(String.format("Invalid argument %d for cents.", cents));
        }
        this.balanceCents -= cents;
    }

    public void setBalance(int balanceCents){
        this.balanceCents = balanceCents;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }


    public int getMaxDeposit() {
        return maxDeposit;
    }

    public void setMaxDeposit(int maxDeposit) {
        this.maxDeposit = maxDeposit;
    }
}
