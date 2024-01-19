public class Account {
    private String name;
    private double balance;
    private Customer accOwner;

    public Account(String n, double b, Customer a) {
        name = n;
        balance = b;
        accOwner = a;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

}
