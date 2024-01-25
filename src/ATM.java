import java.util.Scanner;
public class ATM {
    //instance variables
    private Account savings = null;
    private Account checking = null;
    private Customer person = null;
    Scanner scan = new Scanner(System.in);

    //constructor
    public ATM() {
    }

    //starts the ATM
    public void start() {
        welcome();
        options();
    }

    //when the ATM starts
    public void welcome() {
        System.out.println("--------------------");
        System.out.println("Welcome!");
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Create a PIN: ");
        int PIN = scan.nextInt();
        person = new Customer(name, PIN);
        savings = new Account("Savings Account", 0, person);
        checking = new Account("Checking Account", 0, person);
        System.out.println("Savings and Checking accounts created successfully");
    }

    //gives the customer options to chose from, loops until 7 is chosen
    public void options() {
        int checkPIN = -99999;
        while (checkPIN != person.getPIN()) {
            System.out.print("Enter your PIN: ");
            checkPIN = scan.nextInt();
            if (checkPIN != person.getPIN()) {
                System.out.println("Incorrect PIN!");
                TransactionHistory.addHistory("S", "Failed attempt to enter accounts");
            }
        }
        int option = 0;
        while (option != 7) {
            System.out.println("--------------------");
            System.out.println("1. Withdraw money\n" +
                    "2. Deposit money\n" +
                    "3. Transfer money between accounts\n" +
                    "4. Get account balances\n" +
                    "5. Get transaction history\n" +
                    "6. Change PIN\n" +
                    "7. Exit\n");
            System.out.print("Choose a number: ");
            option = scan.nextInt();
            System.out.println("--------------------");
            if (option == 1) {
                option1();
            } else if (option == 2) {
                option2();
            } else if (option == 3) {
                option3();
            } else if (option == 4) {
                option4();
            } else if (option == 5) {
                option5();
            } else if (option == 6) {
                option6();
            } else if (option == 7) {
                System.out.println("Thank you for using this ATM! Have a nice day!");
            } else {
                System.out.println("Invalid option, try again");
            }
        }
    }

    //for withdrawing money from an account
    private void option1() {
        int option2 = 0;
        int withdrawing = -1;
        int twenties = 0;
        int fives = 0;
        int temp;
        String receipt = "";
        Account accountWith = null;
        while (option2 > 2 || option2 < 1) {
            System.out.println("1. Savings Account");
            System.out.println("2. Checking Account");
            System.out.print("Choose an Account: ");
            option2 = scan.nextInt();
            if (option2 == 1) {
                accountWith = savings;
            } else if (option2 == 2) {
                accountWith = checking;
            } else {
                System.out.println("Invalid option, try again");
            }
        }

        System.out.println("How much would you like to withdraw?");
        System.out.print("Must be a multiple of 5: ");
        withdrawing = scan.nextInt();
        if (withdrawing > accountWith.getBalance()) {
            System.out.println(ConsoleUtility.RED + "Insufficient funds!" + ConsoleUtility.RESET);
            receipt = ConsoleUtility.RED + "Failed to withdraw from " + accountWith.getName() + " due to insufficient funds" + ConsoleUtility.RESET;
        } else if (withdrawing % 5 != 0) {
            System.out.println(ConsoleUtility.RED + "Not a multiple of 5!" + ConsoleUtility.RESET);
            receipt = ConsoleUtility.RED + "Failed to withdraw from " + accountWith.getName() + " due to invalid amount request" + ConsoleUtility.RESET;
        } else {
            temp = withdrawing;
            System.out.print("How many $20 bills would you like?: ");
            twenties = scan.nextInt();
            temp -= 20 * twenties;
            if (temp < 0) {
                System.out.println(ConsoleUtility.RED + "Insufficient funds!" + ConsoleUtility.RESET);
                receipt = ConsoleUtility.RED + "Failed to withdraw from " + accountWith.getName() + " due to insufficient funds" + ConsoleUtility.RESET;
            } else if (temp != 0) {
                System.out.println("$" + temp + " remaining to withdraw");
                System.out.print("How many $5 bills would you like?: ");
                fives = scan.nextInt();
                temp -= 5 * fives;
                if (temp < 0) {
                    System.out.println(ConsoleUtility.RED + "Insufficient funds!" + ConsoleUtility.RESET);
                    receipt = ConsoleUtility.RED + "Failed to withdraw from " + accountWith.getName() + " due to insufficient funds" + ConsoleUtility.RESET;
                } else if (temp != 0) {
                    System.out.println(ConsoleUtility.RED + "Not all money has been withdrawn!" + ConsoleUtility.RESET);
                    receipt = ConsoleUtility.RED + "Failed to withdraw from " + accountWith.getName() + " due to requested bills not matching requested money" + ConsoleUtility.RESET;
                } else {
                    accountWith.changeBalance(withdrawing * -1);
                    receipt = ConsoleUtility.GREEN + "$" + withdrawing + " successfully withdrawn from " + accountWith.getName() +  " as " + twenties + " $20 bills and " + fives + " $5 bills" + ConsoleUtility.RESET;
                }
            } else {
                receipt = ConsoleUtility.GREEN + "$" + withdrawing + " successfully withdrawn from " + accountWith.getName() +  " as " + twenties + " $20 bills" + ConsoleUtility.RESET;
            }
        }
        System.out.println(receipt);
        TransactionHistory.addHistory("A", receipt);

    }

    //for depositing money into an account
    private void option2() {
        int option2 = 0;
        double depositing = 0;
        String receipt = "";
        Account accountDep = null;
        while (option2 > 2 || option2 < 1) {
            System.out.println("1. Savings Account");
            System.out.println("2. Checking Account");
            System.out.print("Choose an Account: ");
            option2 = scan.nextInt();
            if (option2 == 1) {
                accountDep = savings;
            } else if (option2 == 2) {
                accountDep = checking;
            } else {
                System.out.println("Invalid option, try again");
            }
        }
        System.out.print("How much would you like to deposit?: ");
        depositing = scan.nextDouble();
        accountDep.changeBalance(depositing);
        receipt = ConsoleUtility.GREEN + "Successfully deposited $" + depositing + " into " + accountDep.getName() + ConsoleUtility.RESET;
        System.out.println(receipt);
        TransactionHistory.addHistory("A", receipt);
    }

    //for transferring money between accounts
    private void option3() {
        int option2 = 0;
        double transfer = 0;
        String receipt = "";
        Account toAccount = null;
        Account fromAccount = null;
        while (option2 > 2 || option2 < 1) {
            System.out.println("1. Savings Account");
            System.out.println("2. Checking Account");
            System.out.print("Choose an Account to transfer money to: ");
            option2 = scan.nextInt();
            if (option2 == 1) {
                toAccount = savings;
                fromAccount = checking;
            } else if (option2 == 2) {
                toAccount = checking;
                fromAccount = savings;
            } else {
                System.out.println("Invalid option, try again");
            }
        }
        System.out.println("Transferring money from " + fromAccount.getName() + " to " + toAccount.getName());
        System.out.print("How much would you like to transfer?: ");
        transfer = scan.nextDouble();
        if (fromAccount.getBalance() < transfer) {
            System.out.println(ConsoleUtility.RED + "Insufficient funds!" + ConsoleUtility.RESET);
            receipt = ConsoleUtility.RED + "Failed to transfer money from " + fromAccount.getName() + " to " + toAccount.getName() + " due to insufficient funds" + ConsoleUtility.RESET;
        } else {
            fromAccount.changeBalance(transfer * -1);
            toAccount.changeBalance(transfer);
            receipt = ConsoleUtility.GREEN + "Successfully transferred $" + transfer + " from "  + fromAccount.getName() + " to " + toAccount.getName() + ConsoleUtility.RESET;
        }
        System.out.println(receipt);
        TransactionHistory.addHistory("A", receipt);

    }

    //for viewing account balances
    private void option4() {
        System.out.println("Savings account balance: $" + savings.getBalance());
        System.out.println("Checking account balance: $" + checking.getBalance());
        TransactionHistory.addHistory("S", "Viewed balances of Savings Account and Checking Account");
    }

    //for viewing the transaction history
    private void option5() {
        System.out.println(TransactionHistory.getHistory());
        TransactionHistory.addHistory("S", "Viewed transaction history");
    }

    //for changing the PIN
    private void option6() {
        int checkPIN = -99999;
        int newPIN = person.getPIN();
        String receipt;
        System.out.print("Enter your PIN: ");
        checkPIN = scan.nextInt();
        if (checkPIN != person.getPIN()) {
            System.out.println(ConsoleUtility.RED + "Incorrect PIN!" + ConsoleUtility.RESET);
            receipt = ConsoleUtility.RED + "Failed to change PIN, incorrect PIN entered" + ConsoleUtility.RESET;
            TransactionHistory.addHistory("S", receipt);
        } else {
            while (newPIN == person.getPIN()) {
                System.out.print("What do you want to change your PIN to?: ");
                newPIN = scan.nextInt();
                if (newPIN == person.getPIN()) {
                    System.out.println("New PIN can't be same as old PIN, try again");
                }
            }
            person.setPIN(newPIN);
            receipt = ConsoleUtility.GREEN + "Successfully changed PIN" + ConsoleUtility.RESET;
            System.out.println(receipt);
            TransactionHistory.addHistory("S", receipt);
        }
        System.out.println(receipt);

    }
}


