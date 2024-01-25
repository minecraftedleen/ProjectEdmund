import java.util.Scanner;
public class ATM {
    private Account savings = null;
    private Account checking = null;
    Customer person = null;
    Scanner scan = new Scanner(System.in);

    public ATM() {
    }

    public void start() {
        welcome();
        options();
    }

    public void welcome() {
        System.out.println("--------");
        System.out.println("Welcome!");
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        System.out.println("Create a PIN: ");
        int PIN = scan.nextInt();
        person = new Customer(name, PIN);
        savings = new Account("Savings Account", 0, person);
        checking = new Account("Checking Account", 0, person);
        System.out.println("Savings and Checking accounts created successfully");
    }

    public void options() {
        int option = 0;
        while (option != 7) {
            System.out.println("-----------");
            System.out.println("1. Withdraw money\n" +
                    "2. Deposit money\n" +
                    "3. Transfer money between accounts\n" +
                    "4. Get account balances\n" +
                    "5. Get transaction history\n" +
                    "6. Change PIN\n" +
                    "7. Exit\n");
            System.out.println("Choose a number: ");
            option = scan.nextInt();
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
        private void option1 () {
            int option2 = 0;
            int withdrawing = -1;
            int twenties = 0;
            int fives = 0;
            Account accountWith = null;
            while (option2 != 1 || option2 != 2) {
                System.out.println("Choose an Account");
                System.out.println("Type 1 for Savings Account");
                System.out.println("Type 2 for Checking Account");
                option2 = scan.nextInt();
                if (option2 == 1) {
                    accountWith = savings;
                } else if (option2 == 2) {
                    accountWith = checking;
                } else {
                    System.out.println("Invalid option, try again");
                }
            }
                while (withdrawing % 5 != 0 || withdrawing < accountWith.getBalance()) {
                    System.out.println("How much would you like to withdraw?");
                    System.out.println("Must be a multiple of 5:");
                    withdrawing = scan.nextInt();
                    if (withdrawing < accountWith.getBalance()) {
                        System.out.println("Insufficient funds! Try again");
                    } else if (withdrawing % 5 != 0) {
                        System.out.println("Not a multiple of 5, try again");
                    }
                }

            }

        private void option2 () {
            int option2 = 0;
            double depositing = 0;
            String receipt = "";
            Account accountDep = null;
            while (option2 > 2 || option2 < 1) {
                System.out.println("Choose an Account");
                System.out.println("Type 1 for Savings Account");
                System.out.println("Type 2 for Checking Account");
                option2 = scan.nextInt();
                if (option2 == 1) {
                    accountDep = savings;
                } else if (option2 == 2) {
                    accountDep = checking;
                } else {
                    System.out.println("Invalid option, try again");
                }
            }
            System.out.println("How much would you like to deposit?");
            depositing = scan.nextDouble();
            accountDep.changeBalance(depositing);
            receipt = "Successfully deposited $" + depositing + " into " + accountDep.getName() + " account";
            TransactionHistory.addHistory(receipt);
        }

        private void option3() {

        }

        private void option4 () {
            System.out.println("Savings account balance: $" + savings.getBalance());
            System.out.println("Checking account balance: $" + checking.getBalance());
        }

        private void option5 () {
            System.out.println(TransactionHistory.getHistory());
            ;
        }

        private void option6 () {
            int checkPIN = 0;
            int newPIN = person.getPIN();
            String receipt;
            System.out.println("Enter your PIN: ");
            checkPIN = scan.nextInt();
            if (checkPIN != person.getPIN()) {
                System.out.println("Incorrect PIN!");
                receipt = "Failed to change PIN, incorrect PIN entered";
                System.out.print(receipt);
                TransactionHistory.addHistory(receipt);
            } else {
                while (newPIN == person.getPIN()) {
                    System.out.println("What do you want to change your PIN to?:");
                    newPIN = scan.nextInt();
                    if (newPIN == person.getPIN()) {
                        System.out.println("New PIN can't be same as old PIN, try again");
                    }
                }
                person.setPIN(newPIN);
                receipt = "Successfully changed PIN";
                System.out.println(receipt);
                TransactionHistory.addHistory(receipt);
            }

        }

}

