public class CheckingAccount extends BankAccount {
    private final double overdraftLimit;

    public CheckingAccount(String accountHolderName, double balance, double overdraftLimit) {
        super(accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount < -overdraftLimit) {
            throw new InsufficientFundsException("Withdrawal exceeds overdraft limit");
        }
        subtractFromBalance(amount);
        recordTransaction(TransactionType.WITHDRAW, amount);
        System.out.println("Withdrew " + amount);
        System.out.println("Current balance: " + getBalance());
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}