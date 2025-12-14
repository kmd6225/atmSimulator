package com.practice.atm;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private final int pin;

    // constructor for the Account Class
    public Account(int pin, BigDecimal balance){
        this.pin = pin;
        this.balance = balance;
    }

    // Getters for secure access

    public int getPin(){
        return pin;
    }

    public BigDecimal getBalance(){
        return balance;
    }
    // methods for user interaction
    public boolean verifyPin(int userSuppliedPin) {
        if (userSuppliedPin == this.pin){
            return true;
        } else {return false;}
    }

    public boolean withdraw(BigDecimal withdrawalAmount){
        BigDecimal newBalance;
        newBalance = this.balance.subtract(withdrawalAmount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0){
            this.balance = newBalance;
            return true;
        } else{ return false;}
    };




}
