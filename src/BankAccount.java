public class BankAccount {
    private String customerName;
    private String accountNumber;
    private double balance;

    /**
     * Constructor to initialize a bank account
     *
     * @param customerName The name of the account holder
     * @param accountNumber The unique account number
     * @param initialBalance The initial account balance
     */
    public BankAccount(String customerName, String accountNumber, double initialBalance) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;

        if (initialBalance < 0) {
            Logger.getInstance().error("Initial balance cannot be negative. Setting to 0.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }

        Logger.getInstance().inform("New bank account created for " + customerName + " with account number " + accountNumber);
    }

    /**
     * Deposit money into the account
     *
     * @param amount The amount to deposit
     * @return true if deposit was successful, false otherwise
     */
    public boolean deposit(double amount) {
        if (amount <= 0) {
            Logger.getInstance().error("Deposit amount must be positive. Amount: " + amount);
            return false;
        }

        balance += amount;
        Logger.getInstance().inform("Deposit transaction on bank account " + accountNumber + ". Amount: " + amount);
        return true;
    }

    /**
     * Withdraw money from the account
     *
     * @param amount The amount to withdraw
     * @return true if withdrawal was successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            Logger.getInstance().error("Withdrawal amount must be positive. Amount: " + amount);
            return false;
        }

        if (amount > balance) {
            Logger.getInstance().error("Insufficient funds for withdrawal. Requested: " + amount + ", Available: " + balance);
            return false;
        }

        balance -= amount;
        Logger.getInstance().inform("Withdrawal transaction on bank account " + accountNumber + ". Amount: " + amount);
        return true;
    }

    /**
     * Get the current account balance
     *
     * @return The current balance
     */
    public double getBalance() {
        Logger.getInstance().debug("Balance request for account " + accountNumber);
        return balance;
    }

    /**
     * Get the customer name
     *
     * @return The customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get the account number
     *
     * @return The account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }
}
