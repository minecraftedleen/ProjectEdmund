public class TransactionHistory {
    private static String history = "";

    public TransactionHistory() {}

    public static void addHistory(String h) {
        history += "\n" + h;
    }

    public static String getHistory() {
        if (history.equals("")) {
            return "No transactions";
        }
        return history;
    }
}
