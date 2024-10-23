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
    @ManyToOne
    private User owner;
    private int balanceCents;
    private LocalDate creationDate;

    private int maxDeposit;

    public Account(long id, User owner, int balanceCents, LocalDate creationDate, int maxDeposit) {
        this.id = id;
        this.owner = owner;
        this.balanceCents = balanceCents;
        this.creationDate = creationDate;
        this.maxDeposit = maxDeposit;
    }

    public Account(User owner, int balanceCents, LocalDate creationDate, int maxDeposit) {
        this.owner = owner;
        this.balanceCents = balanceCents;
        this.creationDate = creationDate;
        this.maxDeposit = maxDeposit;

    }


    public Account() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
