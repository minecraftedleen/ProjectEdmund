public class Account {
    //instance variables
    private String name;
    private double balance;
    private Customer accOwner;

    //constructor
    public Account(String n, double b, Customer a) {
        name = n;
        balance = b;
        accOwner = a;
    }

    //returns account name
    public String getName() {
        return name;
    }

    //returns account balance
    public double getBalance() {
        return balance;
    }

    //changes balance, used for withdrawing, depositing, and transferring money
    public void changeBalance(double num) {
        balance += num;
    }

}
