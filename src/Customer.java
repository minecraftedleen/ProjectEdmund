public class Customer {
    //instance variables
    private String name;
    private int PIN;

    //constructor
    public Customer(String n, int p) {
        name = n;
        PIN = p;
    }

    //sets PIN, used to change PIN
    public void setPIN(int p) {
        PIN = p;
    }

    //returns the PIN
    public int getPIN() {return PIN;}
}
