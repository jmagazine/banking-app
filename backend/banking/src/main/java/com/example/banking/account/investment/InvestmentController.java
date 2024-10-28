package com.example.banking.account.investment;

import com.example.banking.base.BankingApplicationController;
import com.example.banking.base.ControllerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.ControllerEventListener;
import java.util.List;

@RestController
@RequestMapping("investments")
public class InvestmentController extends BankingApplicationController {

    private final InvestmentService investmentService;

    private ControllerStatus controllerStatus;
    @Autowired
    public InvestmentController(InvestmentService investmentService){
        super(ControllerStatus.ONLINE);
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
