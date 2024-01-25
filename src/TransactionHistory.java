public class TransactionHistory {
    private static String history = "";
    private static int accTrans = 10000;
    private static int secTrans = 10000;

    public TransactionHistory() {}

    public static void addHistory(String p, String h) {
        String trans;
        if (p.equals("A")) {
            trans = accTrans + "";
            accTrans++;
        } else {
            trans = secTrans + "";
            secTrans++;
        }
        history += "\n" + p + trans.substring(1) + ": " + h;

    }

    public static String getHistory() {
        if (history.equals("")) {
            return "No transactions";
        }
        return history;
    }
}
