public class TransactionHistory {
    private String history = "";

    public TransactionHistory() {}

    public void addHistory(String h) {
        history += "\n" + h;
    }
}
