package com.example.banking.account.investment;

import com.example.banking.account.Account;
import com.example.banking.user.User;
import jakarta.persistence.*;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Entity
@DiscriminatorValue("INVESTMENT")
public class InvestmentAccount extends Account {

    // Key: id of investment
    // Value: quantity of investment
    @ElementCollection
    @CollectionTable(name = "investment_shares", joinColumns = @JoinColumn(name = "account_id"))
    @MapKeyColumn(name = "id")
    @Column(name = "shares")
    private  HashMap<Long, Float> investments;

    @Autowired

    public InvestmentAccount() {

    }
    @Autowired
    public InvestmentAccount(long id, long ownerId, int balanceCents, LocalDate creationDate, int maxDeposit, HashMap<Long, Float> investments) {
        super(id, ownerId, balanceCents, creationDate, maxDeposit);
        this.investments = investments;
    }

    public InvestmentAccount(long ownerId, int balanceCents, LocalDate creationDate, int maxDeposit, HashMap<Long, Float> investments) {
        super(ownerId, balanceCents, creationDate, maxDeposit);
        this.investments = investments;
    }

    public InvestmentAccount(HashMap<Long, Float> investments) {
        this.investments = investments;
    }



    public void addInvestment(Long investmentId, float quantity){
        if (quantity < 0){
            throw new IllegalArgumentException("Quantity must be larger than 0");
        }
        if (investments.containsKey(investmentId)){
            investments.put(investmentId, investments.get(investmentId) + quantity);
        }else {
            investments.put(investmentId, quantity);

        }
    }
    public int getValue() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }
}
