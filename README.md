Java practice project that implements a console based ATM application. This application contains two classes: the account class and the atm class.

The account class represents a user's account and contains private fields such as the PIN number and the account balance as well as methods to handle the withdrawing and depositing of funds. The account class also prevents users from withdrawing more funds than are available in their account. By using private fields for the PIN and account balance, access to this information is tightly controlled, which protects against unauthorized access or data corruption.

The ATM class is the driver program of the application. The main method of the ATM class creates a new object instance of the account class by calling the account constructor and sets the default values for the PIN and balance. For the sake of this example, we assume the creation of the account and the setting of the PIN is handled by a seperate application at the bank. Therefore, we set the PIN value to 1234 and the starting balance at 10000 when calling the account constructor.

The ATM main method handles authentication by prompting the user for a PIN. It then passess this PIN value to the verifyPin() method of the account object, which determines if the PIN number supplied by the user matches the account's PIN. If the verifyPin() method determines that the PIN value is incorrect, it returns FALSE to the ATM main method. The main method of the ATM class keeps track of the user's attempts to supply the PIN, and if the user exceeds 3 attempts the program terminates with a message that the user has been locked out of their account.

The ATM main method then uses a switch statement within the while loop to display menu options.

Users can do the following:
1. View balance
2. Withdraw Funds
3. Deposit Funds
4. Exit

The switch statement then calls the relevant method of the account object to carry out the desired actions. Additionally, try...catch blocks within the main method of the ATM class ensure that valid numbers are entered for the options as well as the widthrawal and desposit amounts. Once the user selects object 4, the user session ends.
