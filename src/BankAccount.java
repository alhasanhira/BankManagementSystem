import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    private static int accountCounter = 1000;

    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private final List<Transaction> transactionHistory = new ArrayList<>();
    private Customer owner;

    public BankAccount(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        accountCounter++;
        this.accountNumber = "ACC" + accountCounter;
    }

    public abstract String getAccountType();

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }

    public void setOwner(Customer owner) { this.owner = owner; }
    public Customer getOwner() { return owner; }

    protected void addToBalance(double amount) { this.balance = this.balance + amount; }
    protected void subtractFromBalance(double amount) { this.balance = this.balance - amount; }
    protected void recordTransaction(TransactionType type, double amount) {
        transactionHistory.add(new Transaction(type, amount, balance));
    }
    protected void afterDeposit(double amount) { }

    public void deposit(double amount) { deposit(amount, "Default"); }
    public void deposit(double amount, String note) {
        addToBalance(amount);
        recordTransaction(TransactionType.DEPOSIT, amount);
        System.out.println("Deposited " + amount + " (" + note + ")");
        afterDeposit(amount);
    }

    public double getTotalDeposits() {
        return transactionHistory.stream()
                .filter(t -> t.getType() == TransactionType.DEPOSIT)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds: balance is " + balance + ", tried to withdraw " + amount);
        }
        subtractFromBalance(amount);
        recordTransaction(TransactionType.WITHDRAW, amount);
        System.out.println("Withdrew " + amount);
        System.out.println("Current balance: " + balance);
    }

    public double getTotalWithdrawals() {
        return transactionHistory.stream()
                .filter(t -> t.getType() == TransactionType.WITHDRAW)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public String toString() {
        return getAccountType() + " account [" + accountNumber + "] - " + accountHolderName + "'s balance: " + balance;
    }
}