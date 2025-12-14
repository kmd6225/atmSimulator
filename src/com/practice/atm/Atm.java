package com.practice.atm;

import java.util.Scanner;
import java.math.BigDecimal;

public class Atm {
    static final int pinRetryLimit = 3; //Number of tries a user has before locking their account

    public static void main(String[] args){

        int userSuppliedPin;
        boolean verified = false;
        int pinEntryCntr = 1;
        int userSuppliedOption;

        // Convert the starting balance to a BigDecimal to reduce the likelihood of floating point errors
        BigDecimal startingBalance = new BigDecimal(String.valueOf(500.00));
        Account account = new Account(1234,startingBalance);

        System.out.println("############################");
        System.out.println("#                          #");
        System.out.println("#       ATM System         #");
        System.out.println("#                          #");
        System.out.println("############################");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        /*
        Pin Verification:
            Prompt the user to verify their PIN number. The user has 3 attempts to validate the PIN. If the user fails,
            then that user gets locked out and must work with customer support to rest their PIN.

            Note: The pin retry limit (max number of attempts) is a static variable because all ATM instances
            owned by this company must have the same limit. Allowing this to be an instance variable could lead to inconsistent
            behavior between ATM instances, which would confuse customers.
         */
        while(pinEntryCntr <= pinRetryLimit){
            System.out.println("Please Enter PIN Number: ");
            userSuppliedPin = scanner.nextInt();
            if (account.verifyPin(userSuppliedPin)){
                System.out.println("Success! Pin Verified!");
                verified = true;
                break;
            } else{
                System.out.println("Incorrect Pin! You have " + (pinRetryLimit - pinEntryCntr) + " attempts remaining before locking your account!");
                pinEntryCntr++;
            }
        }

        if (!verified){
            System.out.println("You have exceeded the limit of " + pinRetryLimit + " attempts. You account is now frozen. Contact customer support to unfreeze your account.");
            return;
        }

        // After verifying the user's identify, display the menu

        System.out.println("############################");
        System.out.println("Enter one of the following options to continue: ");
        System.out.println("     Option 1: Check Balance: ");
        System.out.println("     Option 2: Withdraw Balance: ");
        System.out.println("     Option 3: Deposit Balance:  ");
        System.out.println("     Option 4: Exit: ");
        System.out.println("############################");

        BigDecimal userSuppliedWithdrawalAmount;

        while(true){
            System.out.println("Enter an option to continue: ");
            userSuppliedOption = scanner.nextInt();

            switch(userSuppliedOption){
                case 1:
                    System.out.println("Your balance is $" + account.getBalance() + " USD");
                    break;
                case 2:
                    System.out.println("Enter the amount you would like to withdraw :");
                    userSuppliedWithdrawalAmount = new BigDecimal(scanner.next());
                    if (account.withdraw(userSuppliedWithdrawalAmount)){
                        System.out.println("Successfully withdrew $" + userSuppliedWithdrawalAmount + " !");
                        System.out.println("Your new balance is $" + account.getBalance() );
                    } else {
                        System.out.println("Not enough funds to withdraw $" + userSuppliedWithdrawalAmount + " !");
                        }
                    break;

                case 6:
                    System.out.println("Ending user session!");
                    return;

            }
        }




    }
}
