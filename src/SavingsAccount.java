public class SavingsAccount extends BankAccount {
    private final double interestRate;

    public SavingsAccount(String accountHolderName, double balance, double interestRate) {
        super(accountHolderName, balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate;
        addToBalance(interest);
        recordTransaction(TransactionType.INTEREST, interest);
    }

    @Override
    protected void afterDeposit(double amount) {
        addInterest();
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}