package com.example.banking.base;

public class BankingApplicationController {

    private ControllerStatus controllerStatus;

    public BankingApplicationController(ControllerStatus controllerStatus){
        this.controllerStatus = controllerStatus;
    }

    public ControllerStatus getControllerStatus() {
        return controllerStatus;
    }

    public void setControllerStatus(ControllerStatus newControllerStatus){
        this.controllerStatus = newControllerStatus;
    }
}
