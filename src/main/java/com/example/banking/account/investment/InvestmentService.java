package com.example.banking.account.investment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvestmentService {

    private InvestmentRepository investmentRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository){
        this.investmentRepository=investmentRepository;
    }

    @Transactional
    public Investment getInvestment(Long investmentId) {
        Optional<Investment>  investmentOptional = investmentRepository.findById(investmentId);
        if (investmentOptional.isEmpty()){
            throw new IllegalArgumentException(String.format("Investment with id %d not found.", investmentId));
        }
        return investmentOptional.get();
    }
}
