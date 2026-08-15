import java.time.LocalDate;

public class FixedDepositAccount extends BankAccount {
    private final double interestRate;
    private final LocalDate maturityDate;

    public FixedDepositAccount(String accountHolderName, double balance, double interestRate, LocalDate maturityDate) {
        super(accountHolderName, balance);
        this.interestRate = interestRate;
        this.maturityDate = maturityDate;
    }

    @Override
    public void withdraw(double amount) {
        if (LocalDate.now().isBefore(maturityDate)) {
            throw new AccountLockedException("Account is locked until " + maturityDate);
        }
        super.withdraw(amount);
    }

    @Override
    public String getAccountType() {
        return "Fixed Deposit";
    }
}