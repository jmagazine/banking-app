package com.example.banking.account.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("investments")
public class InvestmentController {

    private InvestmentService investmentService;
    @Autowired
    public InvestmentController(InvestmentService investmentService){
        this.investmentService = investmentService;
    }

    @GetMapping
    public List<Investment> getInvestments(@RequestParam(required = false) String investmentType){
        if (investmentType == null || investmentType.isEmpty()){
            return investmentService.getAllInvestments();
        }
        return investmentService.getAllInvestments(investmentType);
    }
}
