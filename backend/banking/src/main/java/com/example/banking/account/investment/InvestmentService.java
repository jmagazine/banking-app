package com.example.banking.account.investment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@EnableScheduling
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

    @Transactional
    public List<Investment> getAllInvestments(){
        return investmentRepository.getAllInvestments();
    }

    @Transactional
    public List<Investment> getAllInvestments(String investmentType){
        return investmentRepository.getAllInvestments(investmentType);
    }

    public float geometricBrownianMotion(float prior, float drift, float volatility, int dt){
        // dt: time in ms
        float dtInSeconds = dt/1000;
        float z = (float) (-0.6 + Math.random())  ;
        float exponent = (drift - 0.5f * volatility * volatility) * dtInSeconds
                + volatility * (float) Math.sqrt(dtInSeconds) * z;
        if (exponent > 10) {
            exponent = 10;  // Limit exponent to prevent explosion
        } else if (exponent < -10) {
            exponent = -10;  // Similarly, avoid going too far negative
        }
        return (float) (prior * Math.exp(exponent));

    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void updateStocks(){
        List<Investment> investments = investmentRepository.getAllInvestments("STOCK");
        for (int i = 0; i < investments.size(); i++){
            Stock stock = (Stock) investments.get(i);
            int last = stock.getLast();
            int high = stock.getHigh();
            int low = stock.getLow();
            float driftMin = 0.00001f;
            float driftMax = 0.00004f;
            float drift = (float) (driftMin + Math.random() * (driftMax-driftMin));
            float volatilityMin = 0.0001f;
            float volatilityMax = 0.0002f;
            float volatility = (float) (volatilityMin + Math.random() * (volatilityMax-volatilityMin));
            int newPrice = (int) (geometricBrownianMotion(last, drift, volatility, 1000));
            System.out.println("New Price: " + newPrice);
            stock.setLast(newPrice);
            if (newPrice > high){
                stock.setHigh(newPrice);
            }
            if (newPrice < low){
                stock.setLow(newPrice);
            }

        }
    }
}
