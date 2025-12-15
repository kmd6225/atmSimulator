package com.practice.atm;

import java.util.InputMismatchException;
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
        BigDecimal startingBalance = new BigDecimal(String.valueOf(10000.00));
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
        BigDecimal userSuppliedDepositAmount;

        // Outer loop ensures the program continues until the user exits by enter option 4

        while(true){

            // Inner while loop continues until the user enters a valid option. This prevents the user from entering a non-integer option.
            while (true){
                try {
                    System.out.println("Enter an option to continue: ");
                    userSuppliedOption = scanner.nextInt();
                    break;
                } catch (InputMismatchException e){
                    System.out.println("The value you entered is not a valid number. Enter numbers 1,2,3, or 4!");
                    scanner.next(); // needed to clear the input stream of the invalid input
                }
            }

            // switch statement directs program execution to the proper action based on user input
            switch(userSuppliedOption){
                case 1: //prints balance to standard output
                    System.out.println("Your balance is $" + account.getBalance() + " USD");
                    break;
                case 2:
                    /*
                    If the user enters option 2, they are prompted for the withdrawal amount. This total is checked to ensure it can be casted
                    to a BigDecimal. If it can't, then a NumberFormatException is caught and the user is told to enter a valid number. The program
                    then breaks out of the switch statement and re-enters the loop to prompt the user again.
                     */
                    System.out.println("Enter the amount you would like to withdraw: ");
                    try {
                        userSuppliedWithdrawalAmount = new BigDecimal(scanner.next());
                    } catch (NumberFormatException e){
                        System.out.println("Error - please enter a valid number!");
                        break;
                    }
                    /* The withraw method of the account object returns TRUE if the user has enough funds to make the withdrawal.
                        If the user has enough funds, the method also decrements the balance appropriately. If the user does not have enough funds,
                        the balance is not decremented and a value of false is returned.
                     */
                    if (account.withdraw(userSuppliedWithdrawalAmount)){
                        System.out.println("Successfully withdrew $" + userSuppliedWithdrawalAmount + " !");
                        System.out.println("Your new balance is $" + account.getBalance() );
                    } else {
                        System.out.println("Not enough funds to withdraw $" + userSuppliedWithdrawalAmount + " !");
                        }
                    break;

                case 3:
                    System.out.println("Enter the amount to deposit: ");

                    try { // User input for the deposit is checked using similar logic to withdrawals
                        userSuppliedDepositAmount = new BigDecimal(scanner.next());
                    } catch (NumberFormatException e){
                        System.out.println("Error - please enter a valid number!");
                        break;
                    }
                    account.deposit(userSuppliedDepositAmount);
                    System.out.println("Success! Deposited " + userSuppliedDepositAmount);
                    break;
                case 4: // users should enter 4 to exit the ATM session when done
                    System.out.println("Ending user session!");
                    return;
                default: // Catch all to handle cases where a User enters a number other than 1,2,3 or 4
                    System.out.println("Entered Number " + userSuppliedOption + " is not a valid option. Please enter 1,2,3, or 4!");
                    break;
            }
        }
    }
}
