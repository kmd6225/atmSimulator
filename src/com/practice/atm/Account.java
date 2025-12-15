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

    // Getter for secure access

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
        /* Ensure the user has enough funds to cover the withdrawal before decrementing.
            Return True if the user has enough funds else false. The boolean values are used by the main method
            of the ATM class to determine how to proceed.
        */
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0){
            this.balance = newBalance;
            return true;
        } else{ return false;}
    };

    public void deposit(BigDecimal depositAmount){
        this.balance = this.balance.add(depositAmount);
    }

}
