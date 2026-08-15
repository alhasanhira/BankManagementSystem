import java.util.Scanner;

public class WithdrawAction implements AccountAction{
    @Override
    public void execute(BankAccount account, Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        scanner.nextLine();
        try {
            account.withdraw(withdrawAmount);
        } catch (InsufficientFundsException | AccountLockedException e) {
            System.out.println(e.getMessage());
        }
    }
}
