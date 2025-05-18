//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Get the logger instance
        Logger logger = Logger.getInstance();

        // Default level is INFORM (all messages)
        logger.error("This is an error message");
        logger.debug("This is a debug message");
        logger.inform("This is an informational message");

        // Set to DEBUG level (ERROR and DEBUG only)
        logger.setLogLevel(2);
        logger.error("This error should appear");
        logger.debug("This debug should appear");
        logger.inform("This inform should NOT appear");

        // Set to ERROR level only
        logger.setLogLevel(1);
        logger.error("This error should appear");
        logger.debug("This debug should NOT appear");
        logger.inform("This inform should NOT appear");

        // Test with a BankAccount
        BankAccount account = new BankAccount("John Doe", "12345678", 1000);
        account.deposit(500);
        account.withdraw(200);
        account.withdraw(-50); // This should log an error
    }
}